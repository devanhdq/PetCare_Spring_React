package com.petshop.factory;

import com.petshop.model.Patient;
import com.petshop.model.User;
import com.petshop.repository.PatientRepository;
import com.petshop.payload.request.user.UserRegisterRequest;
import com.petshop.service.user.UserAttributesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientFactory {

    private final PatientRepository patientRepository;

    private final UserAttributesMapper userAttributesMapper;

    public User createPatient(UserRegisterRequest request) {
        Patient patient = new Patient();
        userAttributesMapper.setCommonAttributes(request, patient);
        return patientRepository.save(patient);
    }
}
