package org.daewon.phreview.service;


import org.daewon.phreview.dto.reply.ReplyDTO;
import org.daewon.phreview.dto.reply.ReplyReadDTO;
import org.daewon.phreview.dto.reply.ReplyUpdateDTO;



import java.util.List;

public interface ReplyService {

    Long createReply(ReplyDTO replyDTO);

    List<ReplyReadDTO> readReplys(Long phId);
    ReplyReadDTO readReply(Long replyId);

    void updateReply(ReplyUpdateDTO replyUpdateDTO);

    void deleteReply(Long replyId);

     // 특정 사용자가 작성한 댓글 목록 가져오는 메서드
    List<ReplyReadDTO> getRepliesByUserId(Long userId);

}