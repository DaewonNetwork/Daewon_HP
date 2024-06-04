"use client";

import React, { useEffect } from "react";
import ReplyCreateForm from "@/(FSD)/features/reply/ui/ReplyCreateForm";
import { ReviewType } from "@/(FSD)/shareds/types/Review.type";
import { useReadReview } from "../../review/api/useReadReview";
import { useParams, useRouter } from "next/navigation";
import ReviewShared from "@/(FSD)/shareds/ui/ReviewShared";

const ReplyContainer = () => {
    const { reviewId } = useParams<{ reviewId: string }>();
    const { data, refetch, isError } = useReadReview(Number(reviewId));

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
        <div>
            <ReviewShared parentRefetch={refetch} review={review} isWriter={review.review} />
            <ReplyCreateForm parentRefetch={refetch} />
        </div>
    )
}

export default ReplyContainer;