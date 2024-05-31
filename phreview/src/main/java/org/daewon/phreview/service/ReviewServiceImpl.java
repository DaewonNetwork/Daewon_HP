package org.daewon.phreview.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.daewon.phreview.domain.*;
import org.daewon.phreview.dto.PharmacyStarDTO;
import org.daewon.phreview.dto.PharmacyStarDTO;
import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.repository.*;
import org.daewon.phreview.security.exception.PharmacyNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private final ModelMapper modelMapper;
    private final PharmacyStarRepository pharmacyStarRepository;
    private final ReviewImageRepository reviewImageRepository;


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
        // 리뷰 저장
        Review review = Review.builder()
                .reviewText(reviewDTO.getReviewText())
                .star(reviewDTO.getStar())
                .likeIndex(reviewDTO.getLikeIndex())
                .build();
        review.setPharmacy(reviewDTO.getPhId());
        review.setUsers(reviewDTO.getUserId());

        reviewRepository.save(review);

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

                // 파일 타입이 이미지인 경우 썸네일 생성
                boolean isImage = Files.probeContentType(savePath).startsWith("image");
                if (isImage) {
                    // 썸네일 파일명 생성 (s_UUID_원본파일명)
                    File thumbnailFile = new File(uploadPath, "s_" + uuid + "_" + originalName);
                    // 썸네일 생성 (원본 파일, 썸네일 파일, 너비 200px, 높이 200px)
                    Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 200, 200);
                }

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
}