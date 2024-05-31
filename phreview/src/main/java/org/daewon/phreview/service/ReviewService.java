package org.daewon.phreview.service;

import org.daewon.phreview.dto.*;
import org.daewon.phreview.dto.Review.ReviewDTO;
import org.daewon.phreview.dto.Review.ReviewReadDTO;
import org.daewon.phreview.dto.Review.ReviewUpdateDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ReviewService {
    Long createReview(ReviewDTO reviewDTO, MultipartFile file, String uploadPath);

    void updateReview(ReviewUpdateDTO reviewUpdateDTO,Long reviewId);

    void deleteReview(Long reviewId);


    List<ReviewReadDTO> readReviews(Long phId);
    List<ReviewReadDTO> readReviewsByLikeIndexDesc(Long phId);
    List<ReviewReadDTO> readAllReviews();
    List<ReviewReadDTO> readAllReviewsByLikeIndexDesc();
    List<ReviewReadDTO> readReviewsByUser(Long userId);
    List<ReviewReadDTO> readLikedReviewsListByUser();



}