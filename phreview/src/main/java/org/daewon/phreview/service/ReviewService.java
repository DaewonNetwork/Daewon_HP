package org.daewon.phreview.service;

import org.daewon.phreview.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Long createReview(ReviewDTO reviewDTO, MultipartFile file, String uploadPath);

    void updateReview(ReviewUpdateDTO reviewUpdateDTO);

    void deleteReview(Long reviewId);


    List<ReviewReadDTO> readReviews(Long phId);
    List<ReviewReadDTO> readReviewsByLikeIndexDesc(Long phId);
    List<ReviewReadDTO> readAllReviews();
    List<ReviewReadDTO> readAllReviewsByLikeIndexDesc();
    List<ReviewReadDTO> readReviewsByUser(Long userId);



}