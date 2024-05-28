package org.daewon.phreview.repository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Review;
import org.daewon.phreview.domain.ReviewImage;
import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.dto.ReviewListAllDTO;
import org.daewon.phreview.service.ReviewService;
import org.daewon.phreview.service.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ReviewImageRepositoryTests {

    @Autowired
    private ReviewImageRepository reviewImageRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private ReviewServiceImpl reviewServiceImpl;
    @Autowired
    private ReviewService reviewService;

    @Test
    public void testInsertWithImages() {
        Review review = Review.builder()
                .reviewText("키보드")
                .build();

        for (int i = 0; i < 3; i++) {
            review.addImage(UUID.randomUUID().toString(), "file" + i + ".jpg");
        }

        reviewImageRepository.save(review);
    }

    @Test
    public void testReadWithImages() {
        Optional<Review> result = reviewImageRepository.findByIdWithImages(112L);

        Review review = result.orElseThrow();

        log.info(review);
        log.info("--------------");
        log.info(review.getImageSet());
        for (ReviewImage reviewImage : review.getImageSet()) {
            log.info(reviewImage);
        }
    }

    @Transactional
    @Commit
    @Test
    public void testModifyImages() {
        Optional<Review> result = reviewImageRepository.findByIdWithImages(112L);
        Review review = result.orElseThrow();

        review.clearImage();

        for(int i = 0;i < 2; i++) {
            review.addImage(UUID.randomUUID().toString(), "Updatefile" + i + ".jpg");
        }

        reviewImageRepository.save(review);
    }

    @Test
    public void testInsertAll() {
        for(int i = 0; i <= 20; i++) {
            Review review = Review.builder()
                    .reviewText("Text" + i)
                    .build();

            for (int j = 0; j < 3; j++) {
                if(i % 5 == 0) {
                    continue;
                }
                review.addImage(UUID.randomUUID().toString(), i + " file" + j + ".jpg");
            }
            reviewImageRepository.save(review);
        }
    }

    @Transactional
    @Test
    public void testSearchImage() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("reviewId").descending());

        Page<ReviewListAllDTO> result = reviewImageRepository.searchWithAll(null, null, pageable);

        log.info("-------------");
        log.info(result.getTotalElements());

        result.getContent().forEach(reviewListAllDTO -> log.info(reviewListAllDTO));

    }

    @Test
    public void testRegisterWithImages() {
        log.info(reviewServiceImpl.getClass().getName());

        ReviewDTO reviewDTO = ReviewDTO.builder()
                .reviewId(110L)
                .reviewText("sample")
                .build();
        reviewDTO.setFileNames(
                Arrays.asList(
                        UUID.randomUUID()+"_aaa.jpg",
                        UUID.randomUUID()+"_bbb.jpg",
                        UUID.randomUUID()+"_bbb.jpg"
                ));

        Long reviewId = reviewService.createReview(reviewDTO);

        log.info("reviewId : " + reviewId);
    }

}
