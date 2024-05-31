package org.daewon.phreview.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.EnjoyPh;
import org.daewon.phreview.domain.LikeForReview;

import org.daewon.phreview.domain.Review;
import org.daewon.phreview.domain.Users;

import org.daewon.phreview.dto.Pharmacy.PharmacyEnjoyRankListDTO;
import org.daewon.phreview.dto.Review.ReviewReadDTO;
import org.daewon.phreview.dto.ReviewDTO;

import org.daewon.phreview.dto.ReviewReadDTO;
import org.daewon.phreview.repository.LikeRepository;
import org.daewon.phreview.repository.ReviewRepository;
import org.daewon.phreview.repository.UserRepository;
import org.daewon.phreview.security.exception.ReviewNotFoundException;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public void likeReview(Long reviewId) { // 좋아요 기능

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        log.info("이름:"+currentUserName);

        Users users = userRepository.findByEmail(currentUserName)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없음"));

        Long userId = users.getUserId();

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException(reviewId));

        if (likeRepository.findByReviewAndUsers(reviewId, userId) == null) {
            review.setLikeIndex(review.getLikeIndex() + 1);
            reviewRepository.save(review);
            LikeForReview likeForReview = new LikeForReview(review,users);
            likeRepository.save(likeForReview);
        } else {
            LikeForReview likeForReview = likeRepository.findByReviewAndUsers(reviewId, userId);
            review.setLikeIndex(review.getLikeIndex()-1);
            reviewRepository.save(review);
            likeForReview.unLikeReview(review);
            likeRepository.delete(likeForReview);
        }

    }

    @Override
    public List<ReviewReadDTO> likedReviewsListByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserName = authentication.getName();
        log.info("이름:"+currentUserName);
        Users users = userRepository.findByEmail(currentUserName)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없음"));

        Long userId = users.getUserId();

        List<Review> list = reviewRepository.listAll();

        return list.stream()
                .map(e -> {
                    PharmacyEnjoyRankListDTO dto = new PharmacyEnjoyRankListDTO();
                    dto.setPhName(e.getPharmacyEnjoy().getPharmacy().getPhName());
                    dto.setPhAdd(e.getPharmacyEnjoy().getPharmacy().getPhAdd());
                    dto.setPhTel(e.getPharmacyEnjoy().getPharmacy().getPhTel());
                    dto.setEnjoyIndex(e.getPharmacyEnjoy().getEnjoyIndex());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
