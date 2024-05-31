package org.daewon.phreview.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.domain.Reply;
import org.daewon.phreview.domain.Review;
import org.daewon.phreview.domain.Users;
import org.daewon.phreview.dto.PageRequestDTO;
import org.daewon.phreview.dto.PageResponseDTO;
import org.daewon.phreview.dto.PharmacyDTO;
import org.daewon.phreview.dto.ReplyDTO;
import org.daewon.phreview.repository.ReplyRepository;
import org.daewon.phreview.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long createReply(ReplyDTO replyDTO) { // 답글 등록
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        reply.setReview(replyDTO.getReviewId());
        reply.setUsers(replyDTO.getUserId());
        Long replyId = replyRepository.save(reply).getReplyId();




        return replyId;
    }

    @Override
    public List<ReplyDTO> readReply(Long reviewId) {
        List<Reply> result = replyRepository.listOfReview(reviewId);
        List<ReplyDTO> replyDTOList = new ArrayList<>();
        for(Reply r : result){
            ReplyDTO dto = ReplyDTO.builder()
                    .replyId(r.getReplyId())
                    .replyText(r.getReplyText())
                    .userId(r.getUsers().getUserId())
                    .reviewId(r.getReview().getReviewId())
                    .createAt(r.getCreateAt())
                    .updateAt(r.getUpdateAt())
                    .build();
            replyDTOList.add(dto);
            log.info("dto" + dto);
        }
        return replyDTOList;
    }

    @Override
    public void updateReply(ReplyDTO replyDTO) {   // 댓글 수정
        Optional<Reply> replyOptional = replyRepository.findById(replyDTO.getReplyId());
        Reply reply = replyOptional.orElseThrow();

        reply.setReplyText(replyDTO.getReplyText());   // 리뷰 내용 수정
        replyRepository.save(reply);
    }

    @Override
    public void deleteReply(Long replyId) {
        replyRepository.deleteById(replyId);
    }

    // 사용자 ID로 댓글 목록을 조회하는 메서드
    @Override
    public List<ReplyDTO> getRepliesByUserId(Long userId) {
        List<Reply> replies = replyRepository.findByUserId(userId);

        return replies.stream()
                .map(reply -> ReplyDTO.builder()
                        .replyId(reply.getReplyId())
                        .reviewId(reply.getReview().getReviewId())
                        .userId(reply.getUsers().getUserId())
                        .replyText(reply.getReplyText())
                        .createAt(reply.getCreateAt())
                        .updateAt(reply.getUpdateAt())
                        .build())
                .collect(Collectors.toList());
    }
}