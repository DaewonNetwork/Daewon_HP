package org.daewon.phreview.repository;

import org.daewon.phreview.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Query("select r from Reply r where r.review.reviewId = :reviewId")
    List<Reply> listOfReview(Long reviewId);
}