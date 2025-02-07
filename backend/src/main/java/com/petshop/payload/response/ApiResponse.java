package com.petshop.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String action;

    private String message;

    private Object data;
    public ApiResponse(String action, String message) {
        this.action = action;
        this.message = message;
    }
}



