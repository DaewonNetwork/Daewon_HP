package org.daewon.phreview.repository;

import org.daewon.phreview.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select r from Reply r where r.review.reviewId = :reviewId")

    void deleteByReview_ReviewId(Long reviewId);


}
