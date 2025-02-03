package com.petshop.factory;

import com.petshop.exception.UserAlreadyExistsException;
import com.petshop.model.User;
import com.petshop.repository.UserRepository;
import com.petshop.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimpleUserFactory implements UserFactory {
    private UserRepository userRepository;
    private final VeterinarianFactory veterinarianFactory;
    private final AdminFactory adminFactory;
    private final PatientFactory patientFactory;

    @Override
    public User createUser(RegistrationRequest registrationRequest) {
        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new UserAlreadyExistsException("Oops! " + registrationRequest.getEmail() + " already exists.");
        }
        return switch (registrationRequest.getUserType()) {
            case "VET" -> veterinarianFactory.createVeterinarian(registrationRequest);
            case "ADMIN" -> adminFactory.createAdmin(registrationRequest);
            case "PATIENT" -> patientFactory.createPatient(registrationRequest);
            default -> null;
        };
    }
}
