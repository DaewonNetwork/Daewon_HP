package org.daewon.phreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.PageRequestDTO;
import org.daewon.phreview.dto.PageResponseDTO;
import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // ROLE_USER 권한을 가지고 있는 유저만 접근 가능
    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long createReview(@RequestBody ReviewDTO reviewDTO) {
        log.info(reviewDTO);
        Long reviewId;
        try {
             reviewId =  reviewService.register(reviewDTO);
        } catch (RuntimeException e ){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request", e);
        }
        return reviewId;
    }


    @GetMapping(value = "/list/{phId}")
    public PageResponseDTO<ReviewDTO> getList(
            @PathVariable(name = "phId") Long phId,
            PageRequestDTO pageRequestDTO) {
        PageResponseDTO<ReviewDTO> responseDTO = reviewService.getListOfPharmacy(phId, pageRequestDTO);
        log.info("dto:"+responseDTO.getDtoList());
        return responseDTO;
    }

    @PreAuthorize("@reviewAndReplySecurity.isReviewOwner(#reviewId)")
    @DeleteMapping(value = "/{reviewId}")
    public Map<String, String> deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.remove(reviewId);
        return Map.of("result", "success");
    }

    @PreAuthorize("@reviewAndReplySecurity.isReviewOwner(#reviewId)")
    @PutMapping(value = "/{reviewId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> modifyReview(@PathVariable("reviewId") Long reviewId, @RequestBody ReviewDTO reviewDTO) {
        reviewDTO.setReviewId(reviewId);
        reviewService.modify(reviewDTO);
        return Map.of("result", "success");
    }
}