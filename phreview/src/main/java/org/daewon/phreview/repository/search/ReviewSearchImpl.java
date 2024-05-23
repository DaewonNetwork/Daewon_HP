package org.daewon.phreview.repository.search;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import org.daewon.phreview.domain.QReply;
import org.daewon.phreview.domain.QReview;
import org.daewon.phreview.domain.Review;
import org.daewon.phreview.dto.ReviewListAllDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewSearchImpl extends QuerydslRepositorySupport implements ReviewSearch{

    public ReviewSearchImpl() {
        super(Review.class);
    }

    @Override
    public Page<ReviewListAllDTO> searchWithAll(String[] types, String keyword) {

        QReview review = QReview.review;
        QReply reply = QReply.reply;

        JPQLQuery<Review> reviewJPQLQuery = from(review);
        reviewJPQLQuery.leftJoin(reply).on(reply.review.eq(review));

        reviewJPQLQuery.groupBy(review);

        JPQLQuery<Tuple> tupleJPQLQuery = reviewJPQLQuery.select(review, reply.countDistinct());

        List<Tuple> tupleList = tupleJPQLQuery.fetch();

        List<ReviewListAllDTO> dtoList = tupleList.stream().map(tuple -> {
            Review review1 = (Review) tuple.get(review);
            long replyCount = tuple.get(1, Long.class);

            ReviewListAllDTO dto = ReviewListAllDTO.builder()
                    .reviewId(review1.getReviewId())
                    .review_text(review1.getReviewText())
                    .createAt(review1.getCreateAt())
                    .replyCount(replyCount)
                    .build();

            return dto;
        }).collect(Collectors.toList());

        return new PageImpl<>(dtoList);
    }
}

