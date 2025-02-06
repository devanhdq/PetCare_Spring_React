package com.petshop.service.appointment;

import com.petshop.model.Appointment;
import com.petshop.payload.request.appointment.AppointmentUpdateRequest;
import com.petshop.payload.request.appointment.BookAppointmentRequest;

import java.util.List;

public interface IAppointmentService {

    Appointment createAppointment(BookAppointmentRequest request, Long senderId, Long recipientId);

    List<Appointment> getAllAppointments();

    Appointment getAppointmentById(Long appointmentId);

    Appointment getAppointmentByAppointmentNo(String appointmentNo);

    Appointment updateAppointmentById(Long appointmentId, AppointmentUpdateRequest request);

    void deleteAppointmentById(Long appointmentId);

}
