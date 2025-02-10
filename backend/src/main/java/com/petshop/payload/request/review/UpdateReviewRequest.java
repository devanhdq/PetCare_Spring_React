package com.petshop.payload.request.review;

import lombok.Data;

@Data
public class UpdateReviewRequest {

    private int stars;

    private String feedback;
}
