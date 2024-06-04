package org.daewon.phreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.reply.ReplyReadDTO;
import org.daewon.phreview.service.ReplyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyPublicController {

    private final ReplyService replyService;

    @Operation(summary = "Replies Post")
    @GetMapping()
    public List<ReplyReadDTO> readReply(
            @RequestParam(name = "reviewId") Long reviewId) {
        List<ReplyReadDTO> replyReadDTO = replyService.readReply(reviewId);

        return replyReadDTO;
    }
}
