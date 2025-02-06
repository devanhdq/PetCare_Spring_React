package com.petshop.service.user;

import com.petshop.dto.UserDTO;
import com.petshop.model.User;
import com.petshop.payload.request.user.UserRegisterRequest;
import com.petshop.payload.request.user.UserUpdateRequest;

import java.util.List;

public interface IUserService {

    User register(UserRegisterRequest request);

    UserDTO update(Long userId, UserUpdateRequest request);

    UserDTO findUserById(Long userId);

    void delete(Long userId);

    List<UserDTO> getAllUsers();

}
