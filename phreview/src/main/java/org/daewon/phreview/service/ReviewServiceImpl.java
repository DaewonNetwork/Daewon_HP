package org.daewon.phreview.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.daewon.phreview.domain.*;

import org.daewon.phreview.dto.review.ReviewDTO;
import org.daewon.phreview.dto.review.ReviewImageDTO;
import org.daewon.phreview.dto.review.ReviewReadDTO;
import org.daewon.phreview.dto.review.ReviewUpdateDTO;
import org.daewon.phreview.repository.*;

import org.daewon.phreview.repository.Pharmacy.PharmacyStarRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final PharmacyStarRepository pharmacyStarRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final UserRepository userRepository;


    /*
        리뷰 저장 로직 :
            - Review 객체를 생성하고 저장
        파일 저장 로직 :
            - 파일이 존재하는 경우에만 파일을 처리함.
            - 각 파일에 대해 고유한 UUID를 생성하고 저장 경로를 설정
            - 파일을 지정된 경로에 저장하고, 이미지 파일인 경우 썸네일 생성
            - ReviewImage 객체를 생성하여 데이터베이스에 저장
     */
    @Override
    @Transactional
    public Long createReview(ReviewDTO reviewDTO, MultipartFile file, String uploadPath) {
        int star = reviewDTO.getStar();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserName = authentication.getName();

        Users users = userRepository.findByEmail(currentUserName)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없음"));

        // 변환된 문자열을 데이터베이스에 저장
        // 리뷰 저장
        Review review = Review.builder()
                .reviewText(reviewDTO.getReviewText())
                .reviewTitle(reviewDTO.getReviewTitle())
                .star(star)
                .build();
        review.setPharmacy(reviewDTO.getPhId());
        review.setUsers(users.getUserId());
        reviewRepository.save(review);


        PharmacyStar pharmacyStar = pharmacyStarRepository.findByPhId(reviewDTO.getPhId()).orElse(null);
        if (pharmacyStar == null) {
            pharmacyStar = PharmacyStar.builder()
                    .pharmacy(Pharmacy.builder().phId(reviewDTO.getPhId()).build())
                    .starTotal(star)
                    .starAvg(star)
                    .build();
        } else {
            pharmacyStar.setStarTotal(pharmacyStar.getStarTotal() + star);
            double starAvg = Math.round(pharmacyStar.getStarTotal() / reviewRepository.countByPharmacyPhId(reviewDTO.getPhId()) * 10.0) / 10.0;
            pharmacyStar.setStarAvg(starAvg);
        }
        pharmacyStarRepository.save(pharmacyStar);

        // 파일이 존재하는 경우에만 파일 저장 로직 실행
        if (file != null && !file.isEmpty()) {
            // 원본 파일명 가져오기
            String originalName = file.getOriginalFilename();
            // UUID 생성 (파일명 중복 방지)
            String uuid = UUID.randomUUID().toString();
            // 파일을 저장할 경로 생성 (컨트롤러에서 @Value로 지정해준 디렉토리에 UUID_원본파일명 형식으로 저장)
            Path savePath = Paths.get(uploadPath, uuid + "_" + originalName);

            try {
                // 파일을 지정된 경로에 저장
                file.transferTo(savePath.toFile());

                // ReviewImage 엔티티 생성 및 저장
                ReviewImage reviewImage = ReviewImage.builder()
                        .uuid(uuid)
                        .fileName(originalName)
                        .ord(0) // 단일 파일이므로 order는 0으로 설정
                        .review(review)
                        .build();
                reviewImageRepository.save(reviewImage);

            } catch (IOException e) {
                // 파일 저장 또는 썸네일 생성 중 오류가 발생할 경우
                log.error("파일 저장하는 도중 오류가 발생했습니다: ", e);
                throw new RuntimeException("File processing error", e);
            }
        }
        // 저장된 리뷰의 ID 반환
        return review.getReviewId();
    }


@Override
    public void updateReview(ReviewUpdateDTO reviewUpdateDTO, MultipartFile file, String uploadPath) {   // 리뷰 수정
        log.info("getReviewId : " +reviewUpdateDTO.getReviewId());
        Optional<Review> reviewOptional = reviewRepository.findById(reviewUpdateDTO.getReviewId());
        Review review = reviewOptional.orElseThrow();

        PharmacyStar pharmacyStar = pharmacyStarRepository.findByPhId(review.getPharmacy().getPhId()).orElse(null);
        pharmacyStar.setStarTotal(pharmacyStar.getStarTotal()-review.getStar());
        review.setReview(reviewUpdateDTO.getReviewText(),reviewUpdateDTO.getStar());
        pharmacyStar.setStarTotal(pharmacyStar.getStarTotal()+reviewUpdateDTO.getStar());
        double starAvg = Math.round(pharmacyStar.getStarTotal() / reviewRepository.countByPharmacyPhId(review.getPharmacy().getPhId()) * 10.0) / 10.0;
        pharmacyStar.setStarAvg(starAvg);
        reviewRepository.save(review); // 리뷰 내용 수정
        pharmacyStarRepository.save(pharmacyStar); // 별점 수정
        // 파일이 존재하는 경우에만 파일 저장 로직 실행
        if (file != null && !file.isEmpty()) {
            // 원본 파일명 가져오기
            String originalName = file.getOriginalFilename();
            // UUID 생성 (파일명 중복 방지)
            String uuid = UUID.randomUUID().toString();
            // 파일을 저장할 경로 생성 (컨트롤러에서 @Value로 지정해준 디렉토리에 UUID_원본파일명 형식으로 저장)
            Path savePath = Paths.get(uploadPath, uuid + "_" + originalName);

            log.info(uuid);
            log.info(originalName);
            // 파일을 지정된 경로에 저장
            try {
                // 파일을 지정된 경로에 저장
                ReviewImage reviewImage = reviewImageRepository.findByReviewId(reviewUpdateDTO.getReviewId()).orElse(null);
                file.transferTo(savePath.toFile());
                log.info(reviewImage);
                // 기존 객체가 있는 경우 수정
                if (reviewImage != null) {
                    reviewImage.setUuid(uuid);
                    reviewImage.setFileName(originalName);
                    // 다른 필드들도 필요에 따라 수정
                    reviewImageRepository.save(reviewImage);
                } else {
                    // 기존 객체가 없는 경우 새로 생성하여 저장
                    reviewImage = ReviewImage.builder()
                            .uuid(uuid)
                            .fileName(originalName)
                            .ord(0) // 단일 파일이므로 order는 0으로 설정
                            .review(review)
                            .build();
                    reviewImageRepository.save(reviewImage);
                }
            } catch (IOException e) {
                // 파일 저장 또는 썸네일 생성 중 오류가 발생할 경우
                log.error("파일 저장하는 도중 오류가 발생했습니다: ", e);
                throw new RuntimeException("File processing error", e);
            }
        } else{
            reviewImageRepository.deleteByReviewId(reviewUpdateDTO.getReviewId());
        }
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

    @Override
    public ReviewReadDTO readReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        log.info("이름:"+currentUserName);
        Users users = userRepository.findByEmail(currentUserName).orElse(null);

        if(users != null) {
            ReviewReadDTO reviewReadDTO = ReviewReadDTO.builder()
            .reviewId(review.getReviewId())
            .phName(review.getPharmacy() != null ? review.getPharmacy().getPhName() : null)
            .userName(review.getUsers() != null ? review.getUsers().getUserName() : null)
            .reviewText(review.getReviewText())
            .reviewTitle(review.getReviewTitle())
            .star(review.getStar())
            .replyIndex(review.getReplyIndex())
            .createAt(review.getCreateAt().format(formatter))
            .updateAt(review.getUpdateAt().format(formatter))
            .isReview(review.getUsers().getUserId() == users.getUserId())
            .build();
    return reviewReadDTO;
        } else {
            ReviewReadDTO reviewReadDTO = ReviewReadDTO.builder()
            .reviewId(review.getReviewId())
            .phName(review.getPharmacy() != null ? review.getPharmacy().getPhName() : null)
            .userName(review.getUsers() != null ? review.getUsers().getUserName() : null)
            .reviewText(review.getReviewText())
            .reviewTitle(review.getReviewTitle())
            .star(review.getStar())
            .replyIndex(review.getReplyIndex())
            .createAt(review.getCreateAt().format(formatter))
            .updateAt(review.getUpdateAt().format(formatter))
            .isReview(false)
            .build();
    return reviewReadDTO;
        }
    }

    @Override
    public List<ReviewReadDTO> readReviews(Long phId) {
        List<Review> result = reviewRepository.listOfPharmacy(phId);

        List<ReviewReadDTO> reviewDTOList = getReviewDTOList(result);

        return reviewDTOList;
    }



    @Override
    public List<ReviewReadDTO> readAllReviews() {
        List<Review> result = reviewRepository.listAll();
        List<ReviewReadDTO> reviewDTOList = getReviewDTOList(result);

        return reviewDTOList;
    }



    // 사용자 ID로 리뷰 목록을 조회하는 메서드
    @Override
    public List<ReviewReadDTO> readReviewsByUser(Long userId) {
        List<Review> result = reviewRepository.findByUserId(userId);
        List<ReviewReadDTO> reviewDTOList = getReviewDTOList(result);
        return reviewDTOList;
    }



    // 리뷰 정보를 가져와서 DTO 리스트로 변환하는 메서드
    private List<ReviewReadDTO> getReviewDTOList(List<Review> reviews) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        log.info("이름:"+currentUserName);
        Users users = userRepository.findByEmail(currentUserName)
                .orElse(null);
        List<ReviewReadDTO> reviewDTOList = new ArrayList<>();
        if(users != null){
           reviewDTOList = reviews.stream()
                .map(review -> ReviewReadDTO.builder()
                        .reviewId(review.getReviewId())
                        .phName(review.getPharmacy() != null ? review.getPharmacy().getPhName() : null)
                        .userName(review.getUsers() != null ? review.getUsers().getUserName() : null)
                        .reviewText(review.getReviewText())
                        .reviewTitle(review.getReviewTitle())
                        .star(review.getStar())
                        .replyIndex(review.getReplyIndex())
                        .createAt(review.getCreateAt().format(formatter))
                        .updateAt(review.getUpdateAt().format(formatter))
                        .isReview(review.getUsers().getUserId() == users.getUserId())
                        .build())
                .collect(Collectors.toList());
        } else{
            reviewDTOList = reviews.stream()
                    .map(review -> ReviewReadDTO.builder()
                            .reviewId(review.getReviewId())
                            .phName(review.getPharmacy() != null ? review.getPharmacy().getPhName() : null)
                            .userName(review.getUsers() != null ? review.getUsers().getUserName() : null)
                            .reviewText(review.getReviewText())
                            .reviewTitle(review.getReviewTitle())
                            .star(review.getStar())
                            .replyIndex(review.getReplyIndex())
                            .createAt(review.getCreateAt().format(formatter))
                            .updateAt(review.getUpdateAt().format(formatter))
                            .isReview(false)
                            .build())
                    .collect(Collectors.toList());
        }

        return reviewDTOList;
    }



}