package com.petshop.controller;

import com.petshop.dto.EntityConverter;
import com.petshop.dto.UserDTO;
import com.petshop.exception.ResourceNotFoundException;
import com.petshop.exception.UserAlreadyExistsException;
import com.petshop.model.User;
import com.petshop.payload.request.user.UserRegisterRequest;
import com.petshop.payload.request.user.UserUpdateRequest;
import com.petshop.payload.response.ApiResponse;
import com.petshop.service.user.UserService;
import com.petshop.utils.FeedBackMessage;
import com.petshop.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RequestMapping(UrlMapping.USERS)
@RestController
public class UserController {
    private final UserService userService;
    private final EntityConverter<User, UserDTO> entityConverter;


    @PostMapping(UrlMapping.REGISTER)
    public ResponseEntity<ApiResponse> addUser(@RequestBody UserRegisterRequest request) {
        try {
            User theUser = userService.register(request);
            UserDTO registeredUser = entityConverter.mapEntityToDto(theUser, UserDTO.class);
            return ResponseEntity.ok().body(new ApiResponse("Create user", FeedBackMessage.SUCCESS, registeredUser));
        } catch (UserAlreadyExistsException error) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Create user", error.getMessage(), null));
        } catch (Exception error) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Create user", error.getMessage(), null));
        }
    }

    @GetMapping(UrlMapping.GET_USER_BY_ID)
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId) {
        try {
            UserDTO theUser = userService.findUserById(userId);
            return ResponseEntity.ok().body(new ApiResponse("Get user by id", FeedBackMessage.SUCCESS, theUser));
        } catch (ResourceNotFoundException error) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Get user by id", error.getMessage(), null));
        } catch (Exception error) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Get user by id", error.getMessage(), null));
        }
    }

    @GetMapping(UrlMapping.GET_ALL_USERS)
    public ResponseEntity<ApiResponse> getAllUsers() {
        try {
            List<UserDTO> users = userService.getAllUsers();
            return ResponseEntity.status(FOUND).body(new ApiResponse("Get all users!", FeedBackMessage.SUCCESS, users));
        } catch (Exception error) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Get user by id", error.getMessage(), null));
        }
    }


    @PutMapping(UrlMapping.UPDATE_USER_BY_ID)
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequest request) {
        try {
            UserDTO theUser = userService.update(userId, request);
            return ResponseEntity.ok().body(new ApiResponse("Update user", FeedBackMessage.SUCCESS, theUser));
        } catch (ResourceNotFoundException error) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Update user", error.getMessage(), null));
        } catch (Exception error) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Update user", error.getMessage(), null));
        }
    }

    @DeleteMapping(UrlMapping.DELETE_USER_BY_ID)
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
        try {
            userService.delete(userId);
            return ResponseEntity.ok(new ApiResponse("Delete user", FeedBackMessage.SUCCESS, null));
        } catch (ResourceNotFoundException error) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Delete user", error.getMessage(), null));
        } catch (Exception error) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Delete user", error.getMessage(), null));
        }
    }

}
