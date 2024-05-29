package org.daewon.phreview.repository.search;

import com.querydsl.jpa.JPQLQuery;
import org.daewon.phreview.domain.QReview;
import org.daewon.phreview.domain.Review;
import org.daewon.phreview.dto.ReviewImageDTO;
import org.daewon.phreview.dto.ReviewListAllDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewSearchImpl extends QuerydslRepositorySupport implements ReviewSearch {

    public ReviewSearchImpl() {
        super(Review.class);
    }

    @Override
    public Page<Review> search1(Pageable pageable) {
        QReview review = QReview.review;
        JPQLQuery<Review> query = from(review);
        query.where(review.reviewId.gt(0L));
        this.getQuerydsl().applyPagination(pageable, query);
        List<Review> title = query.fetch();
        long count = query.fetchCount();
        return null;
    }

    @Override
    public Page<Review> searchAll(String[] types, String keyword, Pageable pageable) {
        QReview review = QReview.review;
        JPQLQuery<Review> query = from(review);
        query.leftJoin(review.imageSet).fetchJoin();
        query.where(review.reviewId.gt(0L));
        this.getQuerydsl().applyPagination(pageable, query);
        List<Review> list = query.fetch();
        long count = query.fetchCount();
        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<ReviewListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable) {

        QReview review = QReview.review;

        JPQLQuery<Review> reviewJPQLQuery = from(review);
        reviewJPQLQuery.leftJoin(review.imageSet).fetchJoin();

        reviewJPQLQuery.groupBy(review);

        getQuerydsl().applyPagination(pageable, reviewJPQLQuery);

        List<Review> reviewList = reviewJPQLQuery.fetch();

        List<ReviewListAllDTO> dtoList = reviewList.stream().map(review1 -> {
            ReviewListAllDTO dto = ReviewListAllDTO.builder()
                    .review_Id(review1.getReviewId())
                    .review_text(review1.getReviewText())
                    .createAt(review1.getCreateAt())
                    .build();

            List<ReviewImageDTO> imageDTOS = review1.getImageSet().stream().sorted()
                    .map(reviewImage -> ReviewImageDTO.builder()
                            .uuid(reviewImage.getUuid())
                            .fileName(reviewImage.getFileName())
                            .ord(reviewImage.getOrd())
                            .build()
                    ).collect(Collectors.toList());

            dto.setReviewImages(imageDTOS);

            return dto;

        }).collect(Collectors.toList());

        long totalCount = reviewJPQLQuery.fetchCount();

        return new PageImpl<>(dtoList, pageable, totalCount);
    }
}