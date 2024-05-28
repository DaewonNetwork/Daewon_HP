package org.daewon.phreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.PageRequestDTO;
import org.daewon.phreview.dto.PageResponseDTO;
import org.daewon.phreview.dto.ReplyDTO;
import org.daewon.phreview.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @Operation(summary = "Replies Post")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long createReply(@RequestBody ReplyDTO replyDTO) {
        log.info(replyDTO);
        Long replyId;
        try {
            replyId =  replyService.createReply(replyDTO);
        } catch (RuntimeException e ){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request", e);
        }
        return replyId;
    }

    @Operation(summary = "Replies Post")
    @GetMapping(value = "/")
    public List<ReplyDTO> getList(
            @RequestParam(name = "reviewId") Long reviewId) {
        List<ReplyDTO> replyDTO = replyService.readReply(reviewId);
        log.info("댓글의 답글"+replyDTO);
        return replyDTO;
    }

    @DeleteMapping(value = "/")
    public Map<String, String> deleteReply(@RequestParam(name = "replyId") Long replyId) {
        replyService.deleteReply(replyId);
        return Map.of("result", "success");
    }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> modifyReply(@RequestParam(name = "replyId") Long replyId, @RequestBody ReplyDTO replyDTO) {
        replyDTO.setReplyId(replyId);
        replyService.updateReply(replyDTO);
        return Map.of("result", "success");
    }
}