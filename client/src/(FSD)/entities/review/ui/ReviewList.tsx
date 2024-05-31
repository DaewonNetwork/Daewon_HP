"use client";

import { useParams } from "next/navigation";
import React, { useEffect } from "react";
import { useReadReviews } from "../api/useReadReviews";
import { ReviewType } from "@/(FSD)/shareds/types/reviews/Review.type";
import ReviewShared from "@/(FSD)/shareds/ui/ReviewShared";

const ReviewList = () => {
    const { phId } = useParams<{ phId: string }>();
    
    const { data, isError, isLoading, refetch } = useReadReviews(Number(phId));

    const reviewList: ReviewType[] = data;

    useEffect(() => {
        refetch();
    }, [phId]);

    if(isError) return <></>;
    if(isLoading) return <></>;

    return (
        <>
            {
                reviewList.map((review, index) => (
                    <React.Fragment key={index}>
                        <ReviewShared review={review} />
                    </React.Fragment>
                ))
            }
        </>
    );
};

export default ReviewList;