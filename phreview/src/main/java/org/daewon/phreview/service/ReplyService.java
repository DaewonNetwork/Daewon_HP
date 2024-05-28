package org.daewon.phreview.service;


import org.daewon.phreview.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    ReplyDTO read(Long replyId);

    void modify(ReplyDTO replyDTO);

    void remove(Long replyId);

    List<ReplyDTO> getListOfReview(Long reviewId);
}
