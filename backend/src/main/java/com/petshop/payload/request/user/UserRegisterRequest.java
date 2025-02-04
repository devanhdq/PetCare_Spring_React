package com.petshop.payload.request.user;

import lombok.Data;

@Data
public class UserRegisterRequest {

    private Long id;

    private String firstName;

    private String lastName;

    private String gender;

    private String phoneNumber;

    private String email;

    private String password;

    private String userType;

    private boolean isEnable;

    private String specialization;
}
