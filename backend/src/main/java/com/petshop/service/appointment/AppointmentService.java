package com.petshop.service.appointment;

import com.petshop.enums.AppointmentStatus;
import com.petshop.exception.ResourceNotFoundException;
import com.petshop.model.Appointment;
import com.petshop.model.Pet;
import com.petshop.model.User;
import com.petshop.payload.request.appointment.AppointmentUpdateRequest;
import com.petshop.payload.request.appointment.BookAppointmentRequest;
import com.petshop.repository.AppointmentRepository;
import com.petshop.repository.UserRepository;
import com.petshop.service.pet.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final UserRepository userRepository;

    private final PetService petService;

    @Transactional
    @Override
    public Appointment createAppointment(BookAppointmentRequest request, Long senderId, Long recipientId) {
        Optional<User> sender = userRepository.findById(senderId);
        Optional<User> recipient = userRepository.findById(recipientId);
        if (sender.isPresent() && recipient.isPresent()) {

            Appointment appointment = request.getAppointment();
            List<Pet> pets = request.getPets();

            pets.forEach(pet -> pet.setAppointment(appointment));

            List<Pet> savedPets = petService.savePetsForAppointment(pets);
            appointment.setPets(savedPets);

            appointment.addPatient(sender.get());
            appointment.addVeterinarian(recipient.get());
            appointment.setAppointmentNo();
            appointment.setStatus(AppointmentStatus.WAITING_FOR_APPROVED);
            return appointmentRepository.save(appointment);
        }
        throw new ResourceNotFoundException("Oop! Sender or Recipient not found!");
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Oop! Appointment not found."));
    }

    @Override
    public Appointment getAppointmentByAppointmentNo(String appointmentNo) {
        return appointmentRepository.findByAppointmentNo(appointmentNo)
                .orElseThrow(() -> new ResourceNotFoundException("Oop! Appointment not found."));
    }

    @Override
    public Appointment updateAppointmentById(Long appointmentId, AppointmentUpdateRequest request) {
        Appointment existingAppointment = getAppointmentById(appointmentId);
        if (!Objects.equals(existingAppointment.getStatus(), AppointmentStatus.WAITING_FOR_APPROVED)) {
            throw new IllegalStateException("Sorry! this appointment can no longer be updated");
        }
        existingAppointment.setAppointmentDate(LocalDate.parse(request.getAppointmentDate()));
        existingAppointment.setAppointmentTime(LocalTime.parse(request.getAppointmentTime()));
        existingAppointment.setReason(request.getReason());

        return appointmentRepository.save(existingAppointment);
    }

    @Override
    public void deleteAppointmentById(Long appointmentId) {
        appointmentRepository.findById(appointmentId)
                .ifPresentOrElse(appointmentRepository::delete, () -> {
                    throw new ResourceNotFoundException("Oop! Appointment not found.");
                });
    }
}
