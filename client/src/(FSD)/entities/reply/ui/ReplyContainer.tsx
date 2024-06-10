"use client";

import React, { useEffect } from "react";
import ReplyCreateForm from "@/(FSD)/features/reply/ui/ReplyCreateForm";
import { ReviewType } from "@/(FSD)/shareds/types/Review.type";
import { useReviewRead } from "../../review/api/useReviewRead";
import { useParams, useRouter } from "next/navigation";
import ReviewShared from "@/(FSD)/shareds/ui/ReviewShared";

const ReplyContainer = () => {
    const { reviewId } = useParams<{ reviewId: string }>();
    const { data, refetch, isError } = useReviewRead(Number(reviewId));

    const review: ReviewType = data;

    const router = useRouter();

    useEffect(() => {
        refetch();
    }, [router]);

    useEffect(() => {
        if(isError) {
            router.back();
        }
    }, [isError]);

    if(!review) return <></>;
    
    return (
        <>
            <ReviewShared parentRefetch={refetch} review={review} isWriter={review.review} />
            <ReplyCreateForm parentRefetch={refetch} />
        </>
    )
}

export default ReplyContainer;