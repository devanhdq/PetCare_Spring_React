package com.petshop.payload.request.user;

import lombok.Data;

@Data
public class UserUpdateRequest {

    private String firstName;

    private String lastName;

    private String gender;

    private String phoneNumber;

    private String specialization;
}
