package org.daewon.phreview.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Review;
import org.daewon.phreview.domain.ReviewImage;
import org.daewon.phreview.dto.review.ReviewDTO;
import org.daewon.phreview.dto.review.ReviewReadDTO;
import org.daewon.phreview.dto.review.ReviewUpdateDTO;
import org.daewon.phreview.repository.ReviewImageRepository;
import org.daewon.phreview.repository.ReviewRepository;

import org.daewon.phreview.service.ReviewService;
import org.daewon.phreview.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private final JWTUtil jwtUtil;
    private final ReviewRepository reviewRepository;

    @Value("${org.daewon.upload.path}")
    private String uploadPath;
    private ReviewImageRepository reviewImageRepository;

    // ROLE_USER 권한을 가지고 있는 유저만 접근 가능
    @PreAuthorize("hasRole('USER')")
    // Content-Type : multipart/form-data, Accept : application/json 형태 이어야함
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createReview(
            // @RequestPart 어노테이션은 요청의 일부인 특정 파트를 가져오는 데 사용됨.
            // 클라이언트로부터 전달된 'reviewDTO'라는 이름의 JSON 데이터를 문자열 형식으로 받음
            @RequestPart("reviewDTO") String reviewDTOStr,
            // 클라이언트로부터 전달된 파일 리스트를 받음.
            // required = false 로 설정하여 파일이 없어도 요청이 처리됨
            @RequestPart(name = "files", required = false) MultipartFile files) {
        log.info("Review DTO String: " + reviewDTOStr);
        log.info("Files: " + files);

        ReviewDTO reviewDTO;
        try {
            // JSON 문자열을 ReviewDTO 객체로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            // @RequestPart 부분에서 한글 처리하는데 문제가 생겨서 강제로 UTF-8로 변환해 줌
            String decodedReviewDTO = new String(reviewDTOStr.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            reviewDTO = objectMapper.readValue(decodedReviewDTO, ReviewDTO.class);
        } catch (IOException e) {
            // JSON 변환 중 오류가 발생하면 로그를 남기고 예외 발생
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON format", e);
        }

        Long reviewId;
        try {
            // 리뷰 생성 메서드 호출
            reviewId = reviewService.createReview(reviewDTO, files != null && !files.isEmpty() ? files : null, uploadPath);

            return ResponseEntity.ok(reviewId);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request", e);
        }
    }


    // 리뷰 작성한 유저만 삭제 가능
    @PreAuthorize("@reviewAndReplySecurity.isReviewOwner(#reviewId)")
    @DeleteMapping()
    public Map<String, String> deleteReview(@RequestParam(name = "reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return Map.of("result", "success");
    }

    // 리뷰 작성한 유저만 수정 가능
//    @PreAuthorize("@reviewAndReplySecurity.isReviewOwner(#reviewId)")
//    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Map<String, String> updateReview(@RequestParam(name = "reviewId") Long reviewId,
//            @RequestBody ReviewUpdateDTO reviewUpdateDTO) {
//        reviewService.updateReview(reviewUpdateDTO,reviewId);
//        return Map.of("result", "success");
//    }

    // 리뷰 작성한 유저만 수정 가능
    @PreAuthorize("@reviewAndReplySecurity.isReviewOwner(#reviewId)")
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> updateReview(
            @RequestParam(name = "reviewId") Long reviewId, // reviewId를 URL 파라미터로 받음
            @RequestPart("reviewUpdateDTO") String reviewUpdateDTOString, // reviewUpdateDTO를 문자열로 받음
            @RequestPart(name = "files", required = false) MultipartFile files) { // 파일을 받음

        // reviewUpdateDTOString을 올바르게 디코딩
        String decodedReviewDTO = new String(reviewUpdateDTOString.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        // decodedReviewDTO를 ReviewUpdateDTO 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        ReviewUpdateDTO reviewUpdateDTO;
        try {
            reviewUpdateDTO = objectMapper.readValue(decodedReviewDTO, ReviewUpdateDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse reviewUpdateDTO", e); // 변환 실패 시 예외 처리
        }

        // 기존의 updateReview 서비스 호출
        reviewService.updateReview(reviewUpdateDTO, reviewId, files, uploadPath);
        return Map.of("result", "success");
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/read")
    public ReviewReadDTO readReview(@RequestParam(name = "reviewId") Long reviewId) {
        ReviewReadDTO review = reviewService.readReview(reviewId); // 리뷰 최신순
        return review;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/list")
    public List<ReviewReadDTO> readReviews(@RequestParam(name = "phId") Long phId) {
        List<ReviewReadDTO> reviewList = reviewService.readReviews(phId); // 리뷰 최신순
        return reviewList;
    }



    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "모든 리뷰")
    @GetMapping("/AllList")
    public List<ReviewReadDTO> readAllReviews(){ // 리뷰 전체
        List<ReviewReadDTO> reviewlist =  reviewService.readAllReviews();
        return reviewlist;
    }

    private static final String UPLOAD_FOLDER = "C:\\upload\\"; // 업로드듼 폴더(createReview시 파일 경로)
    @Operation(summary = "이미지")
    @GetMapping("/read/image")
    public ResponseEntity<byte[]> readReviewImage(Long reviewId) throws IOException {

        ReviewImage reviewImage = reviewImageRepository.findByReviewId(reviewId).orElse(null);
        if (reviewImage == null) {
            return null;
        }
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