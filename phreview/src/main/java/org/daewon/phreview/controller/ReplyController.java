package org.daewon.phreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.reply.ReplyDTO;
import org.daewon.phreview.dto.reply.ReplyReadDTO;
import org.daewon.phreview.dto.reply.ReplyUpdateDTO;
import org.daewon.phreview.service.ReplyService;
import org.daewon.phreview.utils.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final JWTUtil jwtUtil;

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Replies Post")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
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

    // 작성한 유저만 삭제 가능
    @PreAuthorize("@reviewAndReplySecurity.isReplyOwner(#replyId)")
    @DeleteMapping()
    public Map<String, String> deleteReply(@RequestParam(name = "replyId") Long replyId) {
        replyService.deleteReply(replyId);
        return Map.of("result", "success");
    }

    // 작성한 유저만 수정 가능
    @PreAuthorize("@reviewAndReplySecurity.isReplyOwner(#replyId)")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> modifyReply(@RequestParam(name = "replyId") Long replyId, @RequestBody ReplyUpdateDTO replyUpdateDTO) {
        replyService.updateReply(replyUpdateDTO,replyId);
        return Map.of("result", "success");
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Replies Post")
    @GetMapping()
    public List<ReplyReadDTO> readReply(
            @RequestParam(name = "reviewId") Long reviewId) {
        List<ReplyReadDTO> replyReadDTO = replyService.readReply(reviewId);

        return replyReadDTO;
    }
}