package org.daewon.phreview.service;

import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ReviewServiceTests {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void testRegister() {
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .phId(1L)
                .userId(1L)
                .reviewText("good")
                .userId(1L)
                .star(1)
                .build();

//        reviewDTO.setFileNames(
//                Arrays.asList(
//                        UUID.randomUUID() + "_aaa.jpg",
//                        UUID.randomUUID() + "_bbb.jpg",
//                        UUID.randomUUID() + "_bbb.jpg"
//                ));

        Long reviewId = reviewService.createReview(reviewDTO);
        log.info(reviewId);
    }


    @Test
    public void testRead() {
        List<ReviewDTO> reviewDTO = reviewService.readReview(1L);
        log.info(reviewDTO);
    }

    @Test
    public void testModify() {
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .reviewId(1L)
                .reviewText("siuuuuuuuuuu")
                .build();
        reviewService.updateReview(reviewDTO);
    }

    @Test
    public void testDelete() {


        reviewService.deleteReview(101L);

    }
}
