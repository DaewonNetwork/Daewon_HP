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
    public Long createReview(ReviewDTO reviewDTO) { // 리뷰 등록
        Review review = modelMapper.map(reviewDTO, Review.class);
        review.setPharmacy(reviewDTO.getPhId());
        review.setUsers(reviewDTO.getUserId());
        Long reviewId = reviewRepository.save(review).getReviewId();
        return reviewId;
    }

    @Override
    public List<ReviewDTO> readReview(Long phId) {
        List<Review> result = reviewRepository.listOfPharmacy(phId);
        List<ReviewDTO> reviewDTOList = new ArrayList<>();
        for(Review r : result){
            ReviewDTO dto = ReviewDTO.builder()
                    .reviewId(r.getReviewId())
                    .reviewText(r.getReviewText())
                    .star(r.getStar())
                    .userId(r.getUsers().getUserId())
                    .phId(r.getPharmacy().getPhId())
                    .createAt(r.getCreateAt())
                    .updateAt(r.getUpdateAt())
                    .build();
            reviewDTOList.add(dto);
        }
        return reviewDTOList;
    }

    @Override
    public void updateReview(ReviewDTO reviewDTO) {   // 댓글 수정
        Optional<Review> reviewOptional = reviewRepository.findById(reviewDTO.getReviewId());
        Review review = reviewOptional.orElseThrow();

        review.setReviewText(reviewDTO.getReviewText());   // 리뷰 내용 수정
        reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    // 사용자 ID로 리뷰 목록을 조회하는 메서드
    @Override
    public List<ReviewDTO> getReivewsByUserId(Long userId) {
        List<Review> reviews = reviewRepository.findByUserId(userId);

        return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());
    }
}