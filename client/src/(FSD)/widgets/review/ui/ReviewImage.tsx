"use client";

import { useReadReviewImage } from "@/(FSD)/entities/review/api/useReadReviewImage";
import { ReviewType } from "@/(FSD)/shareds/types/Review.type";
import React, { useEffect } from "react";

const ReviewImage = ({ review }: { review: ReviewType }) => {
    const { data, refetch } = useReadReviewImage(review.reviewId);

    console.log(data);

    useEffect(() => {
                
        refetch();
    }, [review]);


    return (
        <div>
            {data && <img style={{ width: 100, height: 100 }} src={data} alt="" />}
        </div>
    )
}

export default ReviewImage;