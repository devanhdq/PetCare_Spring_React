package com.petshop.service.review;

import com.petshop.model.Review;
import com.petshop.payload.request.review.UpdateReviewRequest;
import org.springframework.data.domain.Page;

public interface IReviewService {
    Review saveReview(Review review, Long reviewerId, Long veterinarianId);

    double getAverageRatingForVeterinarian(Long veterinarianId);

    Review updateReview(Long reviewerId, UpdateReviewRequest review);

    Page<Review> findAllReviewsByUserId(Long uerId, int page, int size);

    void deleteReview(Long reviewerId);
}
