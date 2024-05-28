
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

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Replies Post")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long createReply(@RequestBody ReplyDTO replyDTO) {
        log.info(replyDTO);
        Long replyId;
        try {
            replyId =  replyService.register(replyDTO);
        } catch (RuntimeException e ){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request", e);
        }
        return replyId;
    }

    @Operation(summary = "Replies Post")
    @GetMapping(value = "/list/{reviewId}")
    public List<ReplyDTO> getList(
            @PathVariable(name = "reviewId") Long reviewId) {
        List<ReplyDTO> replyDTO = replyService.getListOfReview(reviewId);
        log.info("댓글의 답글"+replyDTO);
        return replyDTO;
    }

    // 작성한 유저만 삭제 가능
    @PreAuthorize("@reviewAndReplySecurity.isReplyOwner(#replyId)")
    @DeleteMapping(value = "/{replyId}")
    public Map<String, String> deleteReply(@PathVariable("replyId") Long replyId) {
        replyService.remove(replyId);
        return Map.of("result", "success");
    }

    // 작성한 유저만 수정 가능
    @PreAuthorize("@reviewAndReplySecurity.isReplyOwner(#replyId)")
    @PutMapping(value = "/{replyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> modifyReply(@PathVariable("replyId") Long replyId, @RequestBody ReplyDTO replyDTO) {
        replyDTO.setReplyId(replyId);
        replyService.modify(replyDTO);
        return Map.of("result", "success");
    }
}
