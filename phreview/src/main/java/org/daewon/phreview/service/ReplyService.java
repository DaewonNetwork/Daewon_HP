package org.daewon.phreview.service;


import org.daewon.phreview.dto.ReplyDTO;


import java.util.List;

public interface ReplyService {

    Long createReply(ReplyDTO replyDTO);

    List<ReplyDTO> readReply(Long phId);

    void updateReply(ReplyDTO replyDTO);

    void deleteReply(Long replyId);

}
