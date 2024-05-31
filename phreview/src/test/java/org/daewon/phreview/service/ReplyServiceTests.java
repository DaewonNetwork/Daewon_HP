package org.daewon.phreview.service;

import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.Reply.ReplyDTO;
import org.daewon.phreview.dto.ReviewDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {
    @Autowired

    private ReplyService replyService;
    @Test
    public void testRead() {
        List<ReplyDTO> replyDTO = replyService.readReply(1L);
        log.info(replyDTO);
    }
}
