package org.daewon.phreview.service;

import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.domain.Review;
import org.daewon.phreview.domain.ReviewImage;
import org.daewon.phreview.dto.PageRequestDTO;
import org.daewon.phreview.dto.PageResponseDTO;
import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.dto.ReviewListAllDTO;
import org.daewon.phreview.repository.PharmacyRepository;
import org.daewon.phreview.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

public interface ReviewService {
    Long createReview(ReviewDTO reviewDTO);

    List<ReviewDTO> readReview(Long phId);

    void updateReview(ReviewDTO reviewDTO);

    void deleteReview(Long reviewId);

    PageResponseDTO<ReviewListAllDTO> listWithAll(PageRequestDTO pageRequestDTO);

}