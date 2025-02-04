package com.petshop.controller;

import com.petshop.dto.EntityConverter;
import com.petshop.dto.UserDTO;
import com.petshop.exception.UserAlreadyExistsException;
import com.petshop.model.User;
import com.petshop.payload.request.user.UserRegisterRequest;
import com.petshop.payload.response.ApiResponse;
import com.petshop.service.user.UserService;
import com.petshop.utils.FeedBackMessage;
import com.petshop.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor
@RequestMapping(UrlMapping.USERS)
@RestController
public class UserController {
    private final UserService userService;
    private final EntityConverter<User, UserDTO> entityConverter;

    @PostMapping
    public ResponseEntity<ApiResponse> addUser(@RequestBody UserRegisterRequest request) {
        try {
            User theUser = userService.add(request);
            UserDTO registeredUser = entityConverter.mapEntityToDto(theUser, UserDTO.class);
            return ResponseEntity.ok().body(new ApiResponse("Create user", FeedBackMessage.SUCCESS, registeredUser));
        } catch (UserAlreadyExistsException error) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Create user", error.getMessage(), null));
        } catch (Exception error) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Create user", error.getMessage(), null));
        }
    }

}
