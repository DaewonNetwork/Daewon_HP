package org.daewon.phreview.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Reply;
import org.daewon.phreview.domain.Review;
import org.daewon.phreview.domain.Users;
import org.daewon.phreview.dto.reply.ReplyDTO;
import org.daewon.phreview.dto.reply.ReplyReadDTO;
import org.daewon.phreview.dto.reply.ReplyUpdateDTO;
import org.daewon.phreview.repository.ReplyRepository;
import org.daewon.phreview.repository.ReviewRepository;
import org.daewon.phreview.repository.UserRepository;
import org.daewon.phreview.security.exception.ReviewNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Long createReply(ReplyDTO replyDTO) { // 답글 등록
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserName = authentication.getName();

        Users users = userRepository.findByEmail(currentUserName)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없음"));

        Reply reply = modelMapper.map(replyDTO, Reply.class);
        reply.setReview(replyDTO.getReviewId());
        reply.setUsers(users.getUserId());

        Long replyId = replyRepository.save(reply).getReplyId();
        Long reviewId = replyDTO.getReviewId();
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException(reviewId));
        review.setReplyIndex(review.getReplyIndex() + 1);

        return replyId;
    }

    @Override
    public List<ReplyReadDTO> readReply(Long reviewId) {
        List<Reply> result = replyRepository.listOfReview(reviewId);
        List<ReplyReadDTO> replyDTOList = new ArrayList<>();
        for(Reply r : result){
            ReplyReadDTO dto = ReplyReadDTO.builder()
                    .replyText(r.getReplyText())
                    .phName(r.getReview().getPharmacy().getPhName())
                    .userName(r.getUsers().getUserName())
                    .createAt(r.getCreateAt())
                    .updateAt(r.getUpdateAt())
                    .build();
            replyDTOList.add(dto);
            log.info("dto" + dto);
        }
        return replyDTOList;
    }

    @Override
    public void updateReply(ReplyUpdateDTO replyUpdateDTO,Long replyId) {   // 댓글 수정
        Optional<Reply> replyOptional = replyRepository.findById(replyId);
        Reply reply = replyOptional.orElseThrow();

        reply.setReplyText(replyUpdateDTO.getReplyText());   // 리뷰 내용 수정

        replyRepository.save(reply);
    }

    @Override
    public void deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow();
        Long reviewId = reply.getReview().getReviewId();
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException(reviewId));
        review.setReplyIndex(review.getReplyIndex() -1);
        replyRepository.deleteById(replyId);
    }

    // 사용자 ID로 댓글 목록을 조회하는 메서드
    @Override
    public List<ReplyReadDTO> getRepliesByUserId(Long userId) {
        List<Reply> replies = replyRepository.findByUserId(userId);

        return replies.stream()
                .map(reply -> ReplyReadDTO.builder()
                        .userName(reply.getUsers().getUserName())
                        .replyText(reply.getReplyText())
                        .createAt(reply.getCreateAt())
                        .updateAt(reply.getUpdateAt())
                        .build())
                .collect(Collectors.toList());
    }
}