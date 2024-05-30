package org.daewon.phreview.service;


import org.daewon.phreview.dto.ReplyDTO;
import org.daewon.phreview.dto.ReviewDTO;


import java.util.List;

public interface ReplyService {

    Long createReply(ReplyDTO replyDTO);

    List<ReplyDTO> readReply(Long phId);

    void updateReply(ReplyDTO replyDTO);

    void deleteReply(Long replyId);

     // 특정 사용자가 작성한 댓글 목록 가져오는 메서드
    List<ReplyDTO> getRepliesByUserId(Long userId);

}