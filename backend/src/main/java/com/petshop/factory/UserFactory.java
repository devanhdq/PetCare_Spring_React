package com.petshop.factory;

import com.petshop.model.User;
import com.petshop.payload.request.RegistrationRequest;

public interface UserFactory {
    public User createUser(RegistrationRequest request);
}
