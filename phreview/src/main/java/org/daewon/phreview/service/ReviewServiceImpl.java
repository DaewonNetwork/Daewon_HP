package org.daewon.phreview.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.*;
import org.daewon.phreview.dto.PharmacyStarDTO;
import org.daewon.phreview.dto.PharmacyStarDTO;
import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.repository.PharmacyRepository;
import org.daewon.phreview.repository.PharmacyStarRepository;
import org.daewon.phreview.repository.ReviewRepository;
import org.daewon.phreview.repository.UserRepository;
import org.daewon.phreview.security.exception.PharmacyNotFoundException;
import org.modelmapper.ModelMapper;
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
    private final PharmacyStarRepository pharmacyStarRepository;

    @Override
    public Long createReview(ReviewDTO reviewDTO) { // 리뷰 등록

        Review review = modelMapper.map(reviewDTO, Review.class);
        review.setPharmacy(reviewDTO.getPhId());
        review.setUsers(reviewDTO.getUserId());
        reviewRepository.save(review);
        PharmacyStar pharmacyStar = pharmacyStarRepository.findByPhId(reviewDTO.getPhId()).orElse(null);
        if (pharmacyStar == null) {
            pharmacyStar = PharmacyStar.builder()
                    .pharmacy(Pharmacy.builder().phId(reviewDTO.getPhId()).build())
                    .starTotal(reviewDTO.getStar())
                    .starAvg(reviewDTO.getStar())
                    .build();
        } else {
            pharmacyStar.setStarTotal(pharmacyStar.getStarTotal() + reviewDTO.getStar());
            double starAvg = Math.round(pharmacyStar.getStarTotal() / reviewRepository.countByPharmacyPhId(reviewDTO.getPhId()) * 10.0) / 10.0;
            pharmacyStar.setStarAvg(starAvg);
        }
        pharmacyStarRepository.save(pharmacyStar);

        return review.getReviewId();
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
        PharmacyStar pharmacyStar = pharmacyStarRepository.findByPhId(review.getPharmacy().getPhId()).orElse(null);
        pharmacyStar.setStarTotal(pharmacyStar.getStarTotal()-review.getStar());
        review.setReview(reviewDTO.getReviewText(),reviewDTO.getStar());
        pharmacyStar.setStarTotal(pharmacyStar.getStarTotal()+review.getStar());
        double starAvg = Math.round(pharmacyStar.getStarTotal() / reviewRepository.countByPharmacyPhId(review.getPharmacy().getPhId()) * 10.0) / 10.0;
        pharmacyStar.setStarAvg(starAvg);
        reviewRepository.save(review); // 리뷰 내용 수정
        pharmacyStarRepository.save(pharmacyStar); // 별점 수정
    }

    @Override
    public void deleteReview(Long reviewId) {
        Optional<Review> reviewOptional =  reviewRepository.findById(reviewId);
        Review review = reviewOptional.orElseThrow();
        PharmacyStar pharmacyStar = pharmacyStarRepository.findByPhId(review.getPharmacy().getPhId()).orElse(null);
        pharmacyStar.setStarTotal(pharmacyStar.getStarTotal()-review.getStar());
        double starAvg = Math.round(pharmacyStar.getStarTotal() / reviewRepository.countByPharmacyPhId(review.getPharmacy().getPhId()) * 10.0) / 10.0;
        pharmacyStar.setStarAvg(starAvg);

        if(pharmacyStar.getStarAvg() == 0){
            pharmacyStarRepository.delete(pharmacyStar);
        }else {
            pharmacyStarRepository.save(pharmacyStar);
        }

        reviewRepository.deleteById(reviewId);
    }

    // 사용자 ID로 리뷰 목록을 조회하는 메서드
    @Override
    public List<ReviewDTO> getReviewsByUserId(Long userId) {
        List<Review> reviews = reviewRepository.findByUserId(userId);

        // .phId(review.getPharmacy() != null ? review.getPharmacy().getPhId() : null)
        // Review 엔티티의 Pharmacy 관계를 통해 phId를 가져온다.
        // Pharmacy 객체가 null이 아닌 경우에만 phId 값을 추출

        // .userId(review.getUsers() != null ? review.getUsers().getUserId() : null)
        // Review 엔티티의 Users 관계를 통해 userId를 가져온다.
        // Users 객체가 null이 아닌 경우에만 userId 값을 추출
        return reviews.stream()
                .map(review -> ReviewDTO.builder()
                        .reviewId(review.getReviewId())
                        .phId(review.getPharmacy() != null ? review.getPharmacy().getPhId() : null)
                        .userId(review.getUsers() != null ? review.getUsers().getUserId() : null)
                        .reviewText(review.getReviewText())
                        .star(review.getStar())
                        .createAt(review.getCreateAt())
                        .updateAt(review.getUpdateAt())
                        .build())
                .collect(Collectors.toList());
    }



    @Override
    public List<PharmacyStarDTO> getPharmaciesByStarAvgDesc() { // 병원 평점 평균 많은 순부터 내림차순 정렬
        List<PharmacyStar> list = pharmacyStarRepository.findAllByOrderByStarAvgDesc();
        return list.stream()
                .map(p -> {
                    PharmacyStarDTO dto = new PharmacyStarDTO();
                    dto.setPhId(p.getPharmacy().getPhId());
                    dto.setStarAvg(p.getStarAvg());
                    return dto;
                })
                .collect(Collectors.toList());
    }

//    private ReviewDTO mapToDTO(Review review) {
//
//        return ReviewDTO.builder()
//                .reviewId(review.getReviewId())
//                .phId(review.getPharmacy().getPhId())
//                .userId(review.getUsers().getUserId())
//                .reviewText(review.getReviewText())
//                .star(review.getStar())
//                .createAt(review.getCreateAt())
//                .updateAt(review.getUpdateAt())
//                .build();
//    }

}