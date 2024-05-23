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

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    // 테스트 : 있는게시글 중에 댓글 추가... 208번 에 댓글 추가... (insert....)
    @Test
    public void testInsert() {

        Pharmacy pharmacy = Pharmacy.builder().phId(1L).build();
        Users users = Users.builder().userId(1L).build();
        Review review = Review.builder()
                    .pharmacy(pharmacy)
                    .reviewText("약국 댓글 테스트2......")
                    .users(users)
                    .build();
        reviewRepository.save(review);

    }

//    @Transactional  // 쿼리가 다 성공해야 성공 처리...
//    @Test
//    public void testBoardReplies() {
//        // 실제 게시물 번호
//        Long bno = 208L;
//
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());
//
//        Page<Reply> result = replyRepository.listOfBoard(bno, pageable);
//        log.info("게시물의 댓글 수 : "+result.getTotalElements());
//        result.getContent().forEach(reply -> {
//            log.info(reply);
//        });
//
//
//    }
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