package com.petshop.service.review;

import com.petshop.enums.AppointmentStatus;
import com.petshop.exception.ResourceAlreadyExistsException;
import com.petshop.exception.ResourceNotFoundException;
import com.petshop.model.Review;
import com.petshop.repository.AppointmentRepository;
import com.petshop.repository.ReviewRepository;
import com.petshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {

    private final ReviewRepository reviewRepository;

    private final AppointmentRepository appointmentRepository;

    private final UserRepository userRepository;

    @Override
    public void saveReview(Review review, Long reviewerId, Long veterinarianId) {
        //1. Check if the reviewer is same is as the doctor being reviewed
        if (veterinarianId.equals(reviewerId)) {
            throw new IllegalArgumentException("Veterinarians cannot review themselves");
        }
        //2. Check if the reviewer has previously reviewed this doctor
        Optional<Review> existingReview = reviewRepository.findByVeterinarianIdAndPatientId(veterinarianId, reviewerId);
        if (existingReview.isPresent()) {
            throw new ResourceAlreadyExistsException("You have already reviewed this veterinarian, you may edit your previous review");
        }
        //3.Check if the reviewer has gotten a completed appointment with the doctor
        boolean hadCompletedAppointment = appointmentRepository.existsByVeterinarianIdAndPatientIdAndStatus(veterinarianId, reviewerId, AppointmentStatus.COMPLETED);
        if (!hadCompletedAppointment) {
            throw new IllegalStateException("Sorry, only patients who have had a completed appointment with the veterinarian can review them");
        }
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
        return reviewRepository.findAllByUserId(userId, pageRequest);
    }
}
