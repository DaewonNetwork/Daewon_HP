package org.daewon.phreview.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Review;
import org.daewon.phreview.dto.*;
import org.daewon.phreview.repository.ReviewRepository;
import org.daewon.phreview.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    // @Override
    // public Long createReview(ReviewDTO reviewDTO) { // 리뷰 등록
    //      Review review = modelMapper.map(reviewDTO, Review.class);
    //      log.info("review : " + review);
    //      review.setPharmacy(reviewDTO.getPhId());
    //      review.setUsers(reviewDTO.getUserId());
    //      Long reviewId = reviewRepository.save(review).getReviewId();
    //      return reviewId;
    // }
    

    @Override
    public Long createReview(ReviewDTO reviewDTO) { // 리뷰 등록
        Review review = mapToEntity(reviewDTO); // modelMapper 대신 mapToEntity 사용
        Long reviewId = reviewRepository.save(review).getReviewId();
        return reviewId;
    }

    // ReviewDTO를 Review 엔티티로 변환합니다.
    private Review mapToEntity(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setReviewId(reviewDTO.getReviewId());
        review.setPharmacy(reviewDTO.getPhId());
        review.setUsers(reviewDTO.getUserId());
        review.setReviewText(reviewDTO.getReviewText());
        review.setStar(reviewDTO.getStar());
        review.setReviewImage(reviewDTO.getFileName());
        return review;
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

    @Override
    public PageResponseDTO<ReviewListAllDTO> listWithAll(PageRequestDTO pageRequestDTO) {
        return null;
    }



}
