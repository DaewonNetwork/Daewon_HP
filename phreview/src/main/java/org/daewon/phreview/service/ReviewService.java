package org.daewon.phreview.service;

import org.daewon.phreview.dto.PageRequestDTO;
import org.daewon.phreview.dto.PageResponseDTO;
import org.daewon.phreview.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    Long register(ReviewDTO reviewDTO);

    ReviewDTO read(Long reviewId);

    void modify(ReviewDTO reviewDTO);

    void remove(Long reviewId);

    PageResponseDTO<ReviewDTO> getListOfPharmacy(Long bno, PageRequestDTO pageRequestDTO);

    // 작성자 검증 메서드
    boolean isReviewOwner(Long reviewId, String userName);

}
