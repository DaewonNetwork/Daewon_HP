package org.daewon.phreview.service;

import org.daewon.phreview.domain.Review;
import org.daewon.phreview.dto.PageRequestDTO;
import org.daewon.phreview.dto.PageResponseDTO;
import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.dto.ReviewListAllDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {
    Long createReview(ReviewDTO reviewDTO);

    List<ReviewDTO> readReview(Long phId);

    void updateReview(ReviewDTO reviewDTO);

    void deleteReview(Long reviewId);

    PageResponseDTO<ReviewListAllDTO> listWithAll(PageRequestDTO pageRequestDTO);

    default Review dtoToEntity(ReviewDTO reviewDTO) {
        Review review = Review.builder()
                .reviewId(reviewDTO.getReviewId())
                .reviewText(reviewDTO.getReviewText())
                .build();

        if (reviewDTO.getFileNames() != null) {
            reviewDTO.getFileNames().forEach(fileName -> {
                String[] arr = fileName.split("_");
                review.addImage(arr[0], arr[1]);
            });
        }
        return review;
    }

}
