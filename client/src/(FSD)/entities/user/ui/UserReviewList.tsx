"use client";

import React from "react";
import { useUserReviewListRead } from "../api/useUserReviewListRead";
import { ReviewType } from "@/(FSD)/shareds/types/Review.type";
import ReviewItem from "@/(FSD)/widgets/review/ui/ReviewItem";
import TextXlargeShared from "@/(FSD)/shareds/ui/TextXlargeShared";

const UserReviewList = () => {
    const { data } = useUserReviewListRead();

    const reviewList: ReviewType[] = data;

    return (
        <div>
            <TextXlargeShared>작성한 리뷰</TextXlargeShared>
            {
                reviewList && reviewList.map(review => (
                    <React.Fragment key={review.reviewId}>
                        <ReviewItem review={review} isWriter={review.review} />
                    </React.Fragment>
                ))
            }
        </div>
    );
};

export default UserReviewList;