package org.daewon.phreview.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.domain.Review;
import org.daewon.phreview.dto.PageRequestDTO;
import org.daewon.phreview.dto.PageResponseDTO;
import org.daewon.phreview.dto.PharmacyDTO;
import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(ReviewDTO reviewDTO) { // 리뷰 등록
        Review review = modelMapper.map(reviewDTO, Review.class);
        review.setPharmacy(reviewDTO.getPhId());

        Long reviewId = reviewRepository.save(review).getReviewId();
        return reviewId;
    }

    @Override
    public ReviewDTO read(Long reviewId) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        Review review = reviewOptional.orElseThrow();

        ReviewDTO reviewDTO = modelMapper.map(review, ReviewDTO.class);
        log.info("read ReviewDTO : "+reviewDTO);
        reviewDTO.setPhId(review.getPharmacy().getPhId());  // phId 값을 넣어줌

        return reviewDTO;
    }

    @Override
    public void modify(ReviewDTO reviewDTO) {   // 댓글 수정
        Optional<Review> reviewOptional = reviewRepository.findById(reviewDTO.getReviewId());
        Review review = reviewOptional.orElseThrow();

        review.setReviewText(reviewDTO.getReviewText());   // 리뷰 내용 수정
        reviewRepository.save(review);
    }

    @Override
    public void remove(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public PageResponseDTO<ReviewDTO> getListOfPharmacy(Long reviewId,PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("reviewId");
        Page<Review> result = reviewRepository.listOfPharmacy(reviewId,pageable);
//        result.getContent().forEach(i -> log.info("Service에서 searchAll 테스트 : "+i));
        // 변환... Board -> BoardDTO
        List<ReviewDTO> dtoList = result.getContent().stream().map(review -> {
            ReviewDTO reviewDTO = modelMapper.map(review, ReviewDTO.class);
            reviewDTO.setPhId(review.getPharmacy().getPhId());
            return reviewDTO;
        }).collect(Collectors.toList());

        return PageResponseDTO.<ReviewDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

}
