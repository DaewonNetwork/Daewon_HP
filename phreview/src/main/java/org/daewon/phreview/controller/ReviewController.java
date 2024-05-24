package org.daewon.phreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.dto.ReviewImageDTO;
import org.daewon.phreview.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "Replies Post")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Long>> register(@RequestBody ReviewDTO reviewDTO) {
        log.info(reviewDTO);

        Map<String, Long> resultMap = Map.of("reviewId", 1L);

        return ResponseEntity.ok(resultMap);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/register")
    public void registerGET() {

    }

    @GetMapping("/reviews/{reviewId}/images")
    public ResponseEntity<Page<ReviewImageDTO>> getReviewImages(
            @PathVariable Long reviewId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ReviewImageDTO> images = reviewService.getImagesByReviewIdPaginated(reviewId, page, size);
        return ResponseEntity.ok(images);
    }
}
