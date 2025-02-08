package com.petshop.service.review;

import com.petshop.exception.ResourceNotFoundException;
import com.petshop.model.Review;
import com.petshop.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {
    private final ReviewRepository reviewRepository;


    @Override
    public void saveReview(Review review, Long reviewerId, Long veterinarianId) {
        reviewRepository.findById(reviewerId)
                .ifPresentOrElse(existingReview -> {
                    existingReview.setStars(review.getStars());
                    existingReview.setFeedback(review.getFeedback());
                    reviewRepository.save(existingReview);
                }, () -> {
                    throw new ResourceNotFoundException("Oop! Not found review!");
                });
    }

    @Transactional
    @Override
    public double getAverageRatingForVeterinarian(Long veterinarianId) {
        List<Review> reviews = reviewRepository.findByVeterinarianId(veterinarianId);
        return reviews.isEmpty()
                ? 0
                : reviews
                .stream()
                .mapToInt(Review::getStars)
                .average()
                .orElse(0.0);
    }

    @Override
    public void updateReview(Long reviewerId, Review review) {
        reviewRepository.findById(reviewerId)
                .ifPresentOrElse(existingReview -> {
                    existingReview.setStars(review.getStars());
                    existingReview.setFeedback(review.getFeedback());
                    reviewRepository.save(existingReview);
                }, () -> {
                    throw new ResourceNotFoundException("Oop! Not found review!");
                });
    }

    @Override
    public Page<Review> findAllReviewsByUserId(Long userId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
//        List<Review> reviews = reviewRepository.findAllByUserId(userId);
        return reviewRepository.findAllByUserId(userId, pageRequest);
    }
}
