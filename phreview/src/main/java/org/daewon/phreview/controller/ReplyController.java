package org.daewon.phreview.controller;

import io.jsonwebtoken.JwtException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.PageRequestDTO;
import org.daewon.phreview.dto.PageResponseDTO;
import org.daewon.phreview.dto.ReplyDTO;
import org.daewon.phreview.service.ReplyService;
import org.daewon.phreview.utils.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

//    @Operation(summary = "Replies Post")
//    @GetMapping(value = "/")
//    public List<ReplyDTO> getList(
//            @RequestParam(name = "reviewId") Long reviewId) {
//        List<ReplyDTO> replyDTO = replyService.readReply(reviewId);
//        log.info("댓글의 답글"+replyDTO);
//        return replyDTO;
//    }

    // 작성한 유저만 삭제 가능
    @PreAuthorize("@reviewAndReplySecurity.isReplyOwner(#replyId)")
    @DeleteMapping(value = "/")
    public Map<String, String> deleteReply(@RequestParam(name = "replyId") Long replyId) {
        replyService.deleteReply(replyId);
        return Map.of("result", "success");
    }

    // 작성한 유저만 수정 가능
    @PreAuthorize("@reviewAndReplySecurity.isReplyOwner(#replyId)")
    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> modifyReply(@RequestParam(name = "replyId") Long replyId, @RequestBody ReplyDTO replyDTO) {
        replyDTO.setReplyId(replyId);
        replyService.updateReply(replyDTO);
        return Map.of("result", "success");
    }

    // 특정 사용자가 작성한 Reply 목록 조회
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<?> getUserReplies(@RequestHeader("Authorization") String token) {
        try {
            // Bearer 토큰에서 "Bearer " 부분 제거
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            // 토큰에서 payload 추출
            Map<String, Object> claims = jwtUtil.validateToken(token);

            // payload에서 userId 추출
            Long userId = Long.parseLong(claims.get("userId").toString());

            log.info("유저 ID: " + userId);

            // 해당 사용자가 작성한 Reply 가져오기
            List<ReplyDTO> replies = replyService.getRepliesByUserId(userId);

            return ResponseEntity.ok(replies);
        } catch (JwtException e) {
            // 토큰이 유효하지 않으면 401 상태 코드 반환
            return ResponseEntity.status(401).body("Invalid token");
        }
    }
}