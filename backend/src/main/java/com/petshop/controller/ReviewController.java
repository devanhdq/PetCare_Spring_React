package com.petshop.controller;

import com.petshop.exception.ResourceAlreadyExistsException;
import com.petshop.exception.ResourceNotFoundException;
import com.petshop.model.Review;
import com.petshop.payload.request.review.UpdateReviewRequest;
import com.petshop.payload.response.ApiResponse;
import com.petshop.service.review.ReviewService;
import com.petshop.utils.FeedBackMessage;
import com.petshop.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(UrlMapping.REVIEWS)
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    @PostMapping(UrlMapping.SUBMIT_REVIEW)
    public ResponseEntity<ApiResponse> saveReview(@RequestBody Review request,
                                                  @RequestParam Long reviewerId,
                                                  @RequestParam Long veterinarianId) {
        try {
            Review review = reviewService.saveReview(request, reviewerId, veterinarianId);
            return ResponseEntity.ok(new ApiResponse("Add review", FeedBackMessage.SUCCESS, review));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(NOT_ACCEPTABLE).body(new ApiResponse("Add review", e.getMessage(), null));
        } catch (ResourceAlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Add review", e.getMessage(), null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Add review", e.getMessage(), null));
        }
    }


    @GetMapping(UrlMapping.GET_REVIEW)
    public ResponseEntity<ApiResponse> getReviewsByUserId(@PathVariable Long userId,
                                                          @RequestParam int page,
                                                          @RequestParam int size) {
        Page<Review> reviewPage = reviewService.findAllReviewsByUserId(userId, page, size);
        return ResponseEntity.status(FOUND).body(new ApiResponse("Get review by user id", FeedBackMessage.SUCCESS, reviewPage));
    }

    @PutMapping(UrlMapping.UPDATE_REVIEW)
    public ResponseEntity<ApiResponse> updateReview(
            @RequestBody UpdateReviewRequest request,
            @PathVariable Long reviewerId) {
        try {
            Review updateReview = reviewService.updateReview(reviewerId, request);
            return ResponseEntity.ok().body(new ApiResponse("Update review", FeedBackMessage.SUCCESS, updateReview));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Update review", e.getMessage(), null));
        }

    }

    @DeleteMapping(UrlMapping.DELETE_REVIEW)
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable Long reviewerId) {
        try {
            reviewService.deleteReview(reviewerId);
            return ResponseEntity.ok(new ApiResponse("Delete review", FeedBackMessage.SUCCESS, null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Delete review", e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Delete review", e.getMessage(), null));
        }

    }

    @GetMapping(UrlMapping.GET_STARS)
    public ResponseEntity<ApiResponse> getStars(@PathVariable Long veterinarianId) {
        try {
            double stars = reviewService.getAverageRatingForVeterinarian(veterinarianId);
            return ResponseEntity.ok(new ApiResponse("Get stars of veterinarian", FeedBackMessage.SUCCESS, stars));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Get stars of veterinarian", e.getMessage(), null));
        }
    }
}
