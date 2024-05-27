package org.daewon.phreview.service;

import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.ReplyDTO;

import org.daewon.phreview.repository.ReplyRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testRegister() {
        ReplyDTO replyDTO = ReplyDTO.builder()
                .reviewId(1L)
                .replyText("good")
                .userId(1L)
                .build();
        Long replyId = replyService.register(replyDTO);
        log.info(replyId);
    }



    @Test
    public void testRead() {
        Long replyId = 101L;

        ReplyDTO replyDTO = replyService.read(replyId);
        log.info(replyDTO);
    }

    @Test
    public void testModify() {
        ReplyDTO replyDTO = ReplyDTO.builder()
                .replyId(101L)
                .replyText("siuuuuuuuuuu")
                .build();
        replyService.modify(replyDTO);
    }

    @Test
    public void testDelete() {


        replyService.remove(101L);

    }
}
