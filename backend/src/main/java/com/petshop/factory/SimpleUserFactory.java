package com.petshop.factory;

import com.petshop.model.User;
import com.petshop.request.RegistrationRequest;

public class SimpleUserFactory implements UserFactory {
    @Override
    public User createUser(RegistrationRequest registrationRequest) {
        return null;
    }
}
