package org.daewon.phreview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Reply;
import org.daewon.phreview.dto.ReplyDTO;
import org.daewon.phreview.repository.ReplyRepository;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(ReplyDTO replyDTO) { // 리뷰 답글 등록
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        reply.setReview(replyDTO.getReviewId());
        Long replyId = replyRepository.save(reply).getReplyId();
        return replyId;
    }

    @Override
    public ReplyDTO read(Long replyId) {
        Optional<Reply> replyOptional = replyRepository.findById(replyId);
        Reply reply = replyOptional.orElseThrow();

        ReplyDTO replyDTO = modelMapper.map(reply, ReplyDTO.class);
        log.info("read ReplyDTO : "+replyDTO);
        replyDTO.setReviewId(reply.getReview().getReviewId());

        return replyDTO;
    }

    @Override
    public void modify(ReplyDTO replyDTO) {   // 댓글 수정
        Optional<Reply> replyOptional = replyRepository.findById(replyDTO.getReplyId());
        Reply reply = replyOptional.orElseThrow();

        reply.setReplyText(replyDTO.getReplyText());   // 리뷰 내용 수정
        replyRepository.save(reply);
    }

    @Override
    public void remove(Long replyId) {
        replyRepository.deleteById(replyId);
    }

    @Override
    public List<ReplyDTO> getListOfReview(Long reviewId) {
        log.info("아이디:"+reviewId);
        List<Reply> result = replyRepository.listOfReview(reviewId);
        log.info("result:"+result);
        List<ReplyDTO> replyDTOList = new ArrayList<>();
        for(Reply r : result){
            ReplyDTO dto = ReplyDTO.builder()
                    .replyId(r.getReplyId())
                    .reviewId(r.getReview().getReviewId())
                    .replyText(r.getReplyText())
                    .userId(r.getUsers().getUserId())
                    .modDate(r.getModDate())
                    .regDate(r.getRegDate())
                    .build();
            replyDTOList.add(dto);
        }
        log.info("답글"+replyDTOList);
        return replyDTOList;
    }
}
