package com.petshop.service.user;

import com.petshop.dto.EntityConverter;
import com.petshop.dto.UserDTO;
import com.petshop.exception.ResourceNotFoundException;
import com.petshop.factory.UserFactory;
import com.petshop.model.User;
import com.petshop.payload.request.user.UserRegisterRequest;
import com.petshop.payload.request.user.UserUpdateRequest;
import com.petshop.repository.UserRepository;
import com.petshop.service.user.impl.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserFactory userFactory;

    private final UserRepository userRepository;

    private final EntityConverter<User, UserDTO> entityConverter;

    @Override
    public User register(UserRegisterRequest request) {
        return userFactory.createUser(request);
    }

    @Override
    public UserDTO update(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Oop! User not found"));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setGender(request.getGender());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setSpecialization(request.getSpecialization());
        return entityConverter.mapEntityToDto(userRepository.save(user), UserDTO.class);
    }

    @Override
    public UserDTO findUserById(Long userId) {
        User theUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Oop! User not found"));
        return entityConverter.mapEntityToDto(theUser, UserDTO.class);

    }

    @Override
    public void delete(Long userId) {
        userRepository.findById(userId)
                .ifPresentOrElse(userRepository::delete, () -> {
                    throw new ResourceNotFoundException("Oop! User not found");
                });
    }


    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> entityConverter.mapEntityToDto(user, UserDTO.class))
                .collect(Collectors.toList());
    }

}
