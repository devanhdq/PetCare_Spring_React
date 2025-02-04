package com.petshop.factory;

import com.petshop.model.User;
import com.petshop.payload.request.user.UserRegisterRequest;

public interface UserFactory {
    public User createUser(UserRegisterRequest request);
}
