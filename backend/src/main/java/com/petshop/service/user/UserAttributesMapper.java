package com.petshop.service.user;

import com.petshop.model.User;
import com.petshop.payload.request.user.UserRegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class UserAttributesMapper {

    public void setCommonAttributes(UserRegisterRequest source, User target) {
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setGender(source.getGender());
        target.setPhoneNumber(source.getPhoneNumber());
        target.setEmail(source.getEmail());
        target.setPassword(source.getPassword());
        target.setUserType(source.getUserType());
        target.setEnable(source.isEnable());
        target.setSpecialization(source.getSpecialization());
    }
}
