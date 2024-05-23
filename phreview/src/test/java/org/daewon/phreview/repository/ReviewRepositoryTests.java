package org.daewon.phreview.repository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Review;
import org.daewon.phreview.domain.ReviewImage;
import org.daewon.phreview.dto.ReviewListAllDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsertWithImages() {

        Review review = Review.builder()
                .reviewText("첨부파일")
                .build();

        for (int i = 0; i < 3; i++) {
            review.addImage(UUID.randomUUID().toString(), "file" + i + ".jpg");
        }
        reviewRepository.save(review);
    }

    @Test
    public void testReadWithimages() {
        Optional<Review> result = reviewRepository.findByIdWithImages(1L);

        Review review = result.orElseThrow();

        log.info(review);
        log.info("---------------");
        for (ReviewImage reviewImage : review.getImageSet()) {
            log.info(reviewImage);
        }
    }

    @Transactional
    @Commit
    @Test
    public void testModifyImages() {
        Optional<Review> result = reviewRepository.findByIdWithImages(1L);

        Review review = result.orElseThrow();

        review.clearImages();

        for(int i = 0; i < 2; i++) {
            review.addImage(UUID.randomUUID().toString(), "updatefile"  + i + ".jpg");
        }

        reviewRepository.save(review);
    }

    @Test
    @Transactional
    @Commit
    public void testRemoveAll() {
        Long reviewId = 1L;

        replyRepository.deleteByReview_ReviewId(reviewId);

        reviewRepository.deleteById(reviewId);
    }

    @Test
    public void testInsertAll() {
        for(int i = 1; i <= 50; i++) {
            Review review = Review.builder()
                    .reviewText("Content" + i)
                    .build();
            for(int j = 0; j < 3; j++) {
                if(i % 5 == 0) {
                    continue;
                }
                review.addImage(UUID.randomUUID().toString(), i+ "file" + j + ".jpg");
            }
            reviewRepository.save(review);
        }
    }

    @Test
    public void testSearchImageReplyCount() {
        List<ReviewListAllDTO> result = reviewRepository.searchWithAll(null, null);

        log.info("-------------------");
        log.info(result.size());

        result.forEach(review -> {
            log.info(review);
        });
    }


    @Test
    public void delete() {
        reviewRepository.deleteAll();
    }

}
