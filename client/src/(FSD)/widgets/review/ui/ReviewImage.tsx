"use client";

import { useReadReviewImage } from "@/(FSD)/entities/review/api/useReadReviewImage";
import { ReviewType } from "@/(FSD)/shareds/types/Review.type";
import React, { useEffect } from "react";

const ReviewImage = ({ review }: { review: ReviewType }) => {
    const { data } = useReadReviewImage(review.reviewId);

    console.log("____________________________");
    console.log(review);
    console.log(data);
    console.log("----------------------------");


    return (
        <div>

        </div>
    )
}

export default React.memo(ReviewImage);