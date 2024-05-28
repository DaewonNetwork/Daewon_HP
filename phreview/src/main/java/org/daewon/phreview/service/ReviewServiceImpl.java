package org.daewon.phreview.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.domain.Review;
import org.daewon.phreview.domain.Users;
import org.daewon.phreview.dto.PageRequestDTO;
import org.daewon.phreview.dto.PageResponseDTO;
import org.daewon.phreview.dto.PharmacyDTO;
import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.repository.ReviewRepository;
import org.daewon.phreview.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(ReviewDTO reviewDTO) { // 리뷰 등록
        Review review = modelMapper.map(reviewDTO, Review.class);
        review.setPharmacy(reviewDTO.getPhId());
        review.setUsers(reviewDTO.getUserId());
        Long reviewId = reviewRepository.save(review).getReviewId();
        return reviewId;
    }

    @Override
    public ReviewDTO read(Long reviewId) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        Review review = reviewOptional.orElseThrow();

        ReviewDTO reviewDTO = modelMapper.map(review, ReviewDTO.class);
        reviewDTO.setPhId(review.getPharmacy().getPhId());  // phId 값을 넣어줌
        reviewDTO.setUserId(review.getUsers().getUserId());

        log.info("read ReviewDTO : "+reviewDTO);
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
            reviewDTO.setUserId(review.getUsers().getUserId());
            return reviewDTO;
        }).collect(Collectors.toList());
        log.info(dtoList);
        return PageResponseDTO.<ReviewDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    // 현재 사용자가 리뷰의 작성자인지 확인하는 메서드
    @Override
    public boolean isReviewOwner(Long reviewId, String userName) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if (reviewOptional.isPresent()) {
            Review review = reviewOptional.get();
            return review.getUsers().getUserName().equals(userName);
        }

        return false;
    }
}
