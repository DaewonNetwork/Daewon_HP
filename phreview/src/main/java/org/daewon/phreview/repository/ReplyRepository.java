package org.daewon.phreview.repository;

import org.daewon.phreview.domain.Reply;
import org.daewon.phreview.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Query("select r from Reply r where r.review.reviewId = :reviewId")
    List<Reply> listOfReview(Long reviewId);

    // 댓글 작성자의 userId를 반환하도록 하는 메서드
    @Query("select r.users.userId from Reply r where r.replyId = :replyId")
    Optional<Long> findAuthorUserIdById(Long replyId);

    // 사용자 ID로 작성한 댓글을 조회하는 메서드
    @Query("select r from Reply r where r.users.userId = :userId")
    List<Reply> findByUserId(Long userId);
}