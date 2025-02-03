package com.petshop.factory;

import com.petshop.model.Patient;
import com.petshop.model.User;
import com.petshop.repository.PatientRepository;
import com.petshop.request.RegistrationRequest;
import com.petshop.service.user.UserAttributeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientFactory {
    private final PatientRepository patientRepository;
    private final UserAttributeMapper userAttributeMapper;

    public User createPatient(RegistrationRequest request) {
        Patient patient = new Patient();
        userAttributeMapper.setCommonAttributes(request, patient);
        return patientRepository.save(patient);

    }
}
