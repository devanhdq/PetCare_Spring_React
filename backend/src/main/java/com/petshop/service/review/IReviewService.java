package com.petshop.service.review;

import com.petshop.model.Review;
import org.springframework.data.domain.Page;

public interface IReviewService {
    void saveReview(Review review, Long reviewerId, Long veterinarianId);

    double getAverageRatingForVeterinarian(Long veterinarianId);

    void updateReview(Long reviewerId, Review review);

    Page<Review> findAllReviewsByUserId(Long uerId, int page, int size);
}
