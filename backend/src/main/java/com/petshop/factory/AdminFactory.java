package com.petshop.factory;

import com.petshop.model.Admin;
import com.petshop.model.User;
import com.petshop.repository.AdminRepository;
import com.petshop.repository.UserRepository;
import com.petshop.request.RegistrationRequest;
import com.petshop.service.user.UserAttributeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminFactory {
    private final AdminRepository adminRepository;
    private final UserAttributeMapper userAttributeMapper;

    public User createAdmin(RegistrationRequest request) {
        Admin admin = new Admin();
        userAttributeMapper.setCommonAttributes(request, admin);
        return adminRepository.save(admin);
    }
}
