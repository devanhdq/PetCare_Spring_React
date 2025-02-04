package com.petshop.factory;

import com.petshop.exception.UserAlreadyExistsException;
import com.petshop.model.User;
import com.petshop.repository.UserRepository;
import com.petshop.payload.request.user.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimpleUserFactory implements UserFactory {
    private final UserRepository userRepository;
    private final VeterinarianFactory veterinarianFactory;
    private final AdminFactory adminFactory;
    private final PatientFactory patientFactory;

    @Override
    public User createUser(UserRegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Oops! " + request.getEmail() + " already exists.");
        }
        return switch (request.getUserType()) {
            case "VET" -> veterinarianFactory.createVeterinarian(request);
            case "ADMIN" -> adminFactory.createAdmin(request);
            case "PATIENT" -> patientFactory.createPatient(request);
            default -> null;
        };
    }
}
