package org.daewon.phreview.repository;



import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.domain.Reply;
import org.daewon.phreview.domain.Review;
import org.daewon.phreview.domain.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;


    @Test
    public void testInsert() {

        Users users = Users.builder().userId(1L).build();
        Review review = Review.builder().reviewId(2L).build();
        Reply reply = Reply.builder()
                .replyText("약국 답글 테스트1......")
                .review(review)
                .users(users)
                .build();
        replyRepository.save(reply);

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