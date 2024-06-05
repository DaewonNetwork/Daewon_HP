package org.daewon.phreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.ReviewImage;
import org.daewon.phreview.dto.review.ReviewImageDTO;
import org.daewon.phreview.dto.review.ReviewReadDTO;

import org.daewon.phreview.repository.ReviewImageRepository;

import org.daewon.phreview.service.ReviewService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

    @Value("${org.daewon.upload.path}")
    private String uploadPath;

    @GetMapping("/read")
    public ReviewReadDTO readReview(@RequestParam(name = "reviewId") Long reviewId) {
        ReviewReadDTO review = reviewService.readReview(reviewId); // 리뷰 최신순
        return review;
    }

//    @GetMapping("/list")
//    public List<ReviewReadDTO> readReviews(@RequestParam(name = "phId") Long phId) {
//        List<ReviewReadDTO> reviewList = reviewService.readReviews(phId); // 리뷰 최신순
//        return reviewList;
//    }

    @GetMapping("/list")
    public ResponseEntity<?> readReviews(@RequestParam(name="phId")Long phId) throws IOException {
        List<ReviewReadDTO> reviews = reviewService.readReviews(phId);

        for(ReviewReadDTO review : reviews) {
            ReviewImage reviewImage = reviewImageRepository.findByReviewId(review.getReviewId()).orElse(null);
            if(reviewImage != null) {
                review.setReviewImage(getImage(reviewImage.getUuid(),reviewImage.getFileName()));
            } else{
                review.setReviewImage(null);
            }

        }
        return ResponseEntity.ok(reviews);
    }

    public byte[] getImage(String uuid, String fileName) throws IOException {
        String filePath = uploadPath + uuid + "_" + fileName;

        // 파일을 바이트 배열로 읽기
        Path path = Paths.get(filePath);
        byte[] image = Files.readAllBytes(path);
        return image;
    }


    @Operation(summary = "모든 리뷰")
    @GetMapping("/AllList")
    public List<ReviewReadDTO> readAllReviews(){ // 리뷰 전체
        List<ReviewReadDTO> reviewlist =  reviewService.readAllReviews();
        return reviewlist;
    }






}
