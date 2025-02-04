package com.petshop.service.user;

import com.petshop.factory.UserFactory;
import com.petshop.model.User;
import com.petshop.repository.UserRepository;
import com.petshop.payload.request.user.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final UserFactory userFactory;

    public User add(UserRegisterRequest request) {
        return userFactory.createUser(request);
    }
}
