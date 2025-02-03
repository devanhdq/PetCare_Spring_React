package com.petshop.factory;

import com.petshop.model.User;
import com.petshop.request.RegistrationRequest;

public interface UserFactory {
    public User createUser(RegistrationRequest registrationRequest);
}
