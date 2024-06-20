"use client";

import React from "react";
import { useUserReviewListRead } from "../api/useUserReviewListRead";
import { ReviewType } from "@/(FSD)/shareds/types/Review.type";
import ReviewItem from "@/(FSD)/widgets/review/ui/ReviewItem";
import TextXlargeShared from "@/(FSD)/shareds/ui/TextXlargeShared";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";

const UserReviewList = () => {
    const { data } = useUserReviewListRead();

    const reviewList: ReviewType[] = data;

    return (
        <div>
            <InnerShared><TextXlargeShared>작성한 리뷰</TextXlargeShared></InnerShared>
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