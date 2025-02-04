package com.petshop.factory;

import com.petshop.model.Admin;
import com.petshop.model.User;
import com.petshop.repository.AdminRepository;
import com.petshop.payload.request.RegistrationRequest;
import com.petshop.service.user.UserAttributesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminFactory {

    private final AdminRepository adminRepository;

    private final UserAttributesMapper userAttributesMapper;

    public User createAdmin(RegistrationRequest request) {
        Admin admin = new Admin();
        userAttributesMapper.setCommonAttributes(request, admin);
        return adminRepository.save(admin);
    }
}
