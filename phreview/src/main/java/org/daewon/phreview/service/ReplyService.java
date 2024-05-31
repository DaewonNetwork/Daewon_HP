package org.daewon.phreview.service;


import org.daewon.phreview.dto.Reply.ReplyDTO;
import org.daewon.phreview.dto.Reply.ReplyReadDTO;
import org.daewon.phreview.dto.Reply.ReplyUpdateDTO;



import java.util.List;

public interface ReplyService {

    Long createReply(ReplyDTO replyDTO);

    List<ReplyReadDTO> readReply(Long phId);

    void updateReply(ReplyUpdateDTO replyUpdateDTO,Long replyId);

    void deleteReply(Long replyId);

     // 특정 사용자가 작성한 댓글 목록 가져오는 메서드
    List<ReplyReadDTO> getRepliesByUserId(Long userId);

}