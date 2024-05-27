package org.daewon.phreview.repository;



import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.domain.Review;
import org.daewon.phreview.domain.Users;
import org.daewon.phreview.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void testInsert() {
        // 실제 DB에 있는 BNO를 선택
        Pharmacy pharmacy = Pharmacy.builder().phId(1L).build();
        Users users = Users.builder().userId(1L).build();
        IntStream.rangeClosed(1, 100).forEach( i -> {
            Review review = Review.builder()
                    .pharmacy(pharmacy)
                    .reviewText("약국 댓글 테스트1......"+i)
                    .users(users)
                    .star(i)
                    .build();

            reviewRepository.save(review);
        });
//        Reply reply = Reply.builder()
//                .board(board)
//                .replyText("댓글 테스트1......")
//                .replyer("replyer1")
//                .build();
//
//        replyRepository.save(reply);
    }

    @Transactional  // 쿼리가 다 성공해야 성공 처리...
    @Test
    public void testBoardReplies() {
        // 실제 게시물 번호

        Pageable pageable = PageRequest.of(0, 10, Sort.by("reviewId").descending());

        Page<Review> result = reviewRepository.listOfPharmacy(1L, pageable);
        log.info("게시물의 댓글 수 : "+result.getTotalElements());
        result.getContent().forEach(reply -> {
            log.info(reply);
        });


    }
//
//    @Test
//    public void testDelete() {
//        replyRepository.deleteById(99L);
//    }
//
//    @Test
//    public void testTotal() {
//        log.info("count : "+replyRepository.count());
//    }

}