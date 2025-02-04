package com.petshop.controller;

import com.petshop.dto.EntityConverter;
import com.petshop.dto.UserDTO;
import com.petshop.exception.UserAlreadyExistsException;
import com.petshop.model.User;
import com.petshop.payload.request.RegistrationRequest;
import com.petshop.payload.response.ApiResponse;
import com.petshop.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    private final EntityConverter<User, UserDTO> entityConverter;

    @PostMapping
    public ResponseEntity<ApiResponse> addUser(@RequestBody RegistrationRequest request) {
        try {
            User theUser = userService.add(request);
            UserDTO registeredUser = entityConverter.mapEntityToDto(theUser, UserDTO.class);
            return ResponseEntity.ok().body(new ApiResponse("Create user", "Successfully", registeredUser));
        } catch (UserAlreadyExistsException error) {
            return ResponseEntity.ok().body(new ApiResponse("Create user", error.getMessage(), null));
        }
    }

}
