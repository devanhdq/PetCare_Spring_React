package com.petshop.dto;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String gender;

    private String phoneNumber;

    private String email;

    private String userType;

    private boolean isEnable;

    private String specialization;
}
