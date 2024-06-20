"use client";

import React, { useEffect } from "react";
import ReplyCreateForm from "@/(FSD)/features/reply/ui/ReplyCreateForm";
import { ReviewType } from "@/(FSD)/shareds/types/Review.type";
import { useReviewRead } from "../../review/api/useReviewRead";
import { useRouter, useSearchParams } from "next/navigation";
import ReviewItem from "@/(FSD)/widgets/review/ui/ReviewItem";
import useUserStore from "@/(FSD)/shareds/stores/useUserStore";

const ReplyContainer = () => {
    const searchParams = useSearchParams();
    const reviewId = +searchParams.get("reviewId")!;
    
    const { data, refetch, isError } = useReviewRead(reviewId);

    const { isLoggedIn } = useUserStore();

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
            <ReviewItem parentRefetch={refetch} review={review} isWriter={review.review} />
            {isLoggedIn && <ReplyCreateForm parentRefetch={refetch} />}
        </>
    )
}

export default ReplyContainer;