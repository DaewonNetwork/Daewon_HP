package org.daewon.phreview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.domain.Review;
import org.daewon.phreview.dto.PharmacyDTO;
import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
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
    public List<ReviewDTO> getListOfPharmacy(Long phId) {
        List<Review> result = reviewRepository.listOfPharmacy(phId);
        List<ReviewDTO> reviewDTOList = new ArrayList<>();
        for(Review r : result){
            ReviewDTO dto = ReviewDTO.builder()
                    .reviewId(r.getReviewId())
                    .phId(r.getPharmacy().getPhId())
                    .reviewText(r.getReviewText())
                    .star(r.getStar())
                    .createAt(r.getCreateAt())
                    .users(r.getUsers())
                    .userId(r.getUsers().getUserId())
                    .build();
            reviewDTOList.add(dto); // 4
        }

        return reviewDTOList;
    }
}
