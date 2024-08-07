package org.daewon.phreview.service;

import org.daewon.phreview.dto.review.ReviewDTO;
import org.daewon.phreview.dto.review.ReviewReadDTO;
import org.daewon.phreview.dto.review.ReviewUpdateDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ReviewService {
    Long createReview(ReviewDTO reviewDTO, MultipartFile file, String uploadPath);

    void updateReview(ReviewUpdateDTO reviewUpdateDTO, MultipartFile files, String uploadPath);

    void deleteReview(Long reviewId);

    ReviewReadDTO readReview(Long reviewId);

    List<ReviewReadDTO> readReviews(Long phId);
    List<ReviewReadDTO> readAllReviews();
    List<ReviewReadDTO> readReviewsByUser(Long userId);




}