package org.daewon.phreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.ReviewImage;
import org.daewon.phreview.dto.review.ReviewImageDTO;
import org.daewon.phreview.dto.review.ReviewReadDTO;

import org.daewon.phreview.repository.ReviewImageRepository;
import org.daewon.phreview.service.LikeService;
import org.daewon.phreview.service.ReviewService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewPublicController {

    private final ReviewService reviewService;
    private final ReviewImageRepository reviewImageRepository;


    @GetMapping("/read")
    public ReviewReadDTO readReview(@RequestParam(name = "reviewId") Long reviewId) {
        ReviewReadDTO review = reviewService.readReview(reviewId); // 리뷰 최신순
        return review;
    }

    @GetMapping("/list")
    public List<ReviewReadDTO> readReviews(@RequestParam(name = "phId") Long phId) {
        List<ReviewReadDTO> reviewList = reviewService.readReviews(phId); // 리뷰 최신순
        return reviewList;
    }

    @Operation(summary = "좋아요 순")
    @GetMapping("/list/like")
    public List<ReviewReadDTO> readReviewsByLikeIndexDesc(@RequestParam(name = "phId") Long phId){ // 리뷰 좋아요순
        List<ReviewReadDTO> reviewlist = reviewService.readReviewsByLikeIndexDesc(phId);
        return reviewlist;
    }

    @Operation(summary = "모든 리뷰")
    @GetMapping("/AllList")
    public List<ReviewReadDTO> readAllReviews(){ // 리뷰 전체
        List<ReviewReadDTO> reviewlist =  reviewService.readAllReviews();
        return reviewlist;
    }

    @Operation(summary = "모든 리뷰 좋아요순")
    @GetMapping("/AllList/like")
    public List<ReviewReadDTO> readAllReviewsByLikeIndexDesc(){ // 리뷰 전체
        List<ReviewReadDTO> reviewlist =  reviewService.readAllReviewsByLikeIndexDesc();
        return reviewlist;
    }

    private static final String UPLOAD_FOLDER = "C:\\upload\\";
    @Operation(summary = "이미지")
    @GetMapping("/read/image")
    public ResponseEntity<byte[]> readReviewImage(Long reviewId) throws IOException {

        ReviewImage reviewImage = reviewImageRepository.findByReviewId(reviewId);

        String uuid = reviewImage.getUuid();
        String fileName = reviewImage.getFileName();

        String filePath = UPLOAD_FOLDER + uuid+"_"+fileName;

        // 파일을 바이트 배열로 읽기
        Path path = Paths.get(filePath);
        byte[] image = Files.readAllBytes(path);

        // 응답에 이미지와 Content-Type 설정 후 반환
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }


}
