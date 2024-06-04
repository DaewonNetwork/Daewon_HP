"use client";

import React, { useEffect } from "react";
import styles from "@/(FSD)/shareds/styles/ReviewStyle.module.scss";
import type { ReviewType } from "../types/Review.type";
import { useRouter } from "next/navigation";
import ItemShared from "./ItemShared";
import TextLargeShared from "./TextLargeShared";
import StarListShared from "./StarListShared";
import TextMediumShared from "./TextMediumShared";
import ReviewDeleteBtn from "@/(FSD)/features/review/ui/ReviewDeleteBtn";

const ReviewShared = ({ review, parentRefetch, isWriter = false }: { review: ReviewType; parentRefetch?: any; isWriter?: boolean }) => {
    const router = useRouter();

    if (!review) return <></>;


    return (
        <div onClick={_ => router.push(`/reply/create/${review.reviewId}`)} className={styles.review_item}>
            <ItemShared>
                <div className="">
                    <div className={styles.review_header}>
                        <div className={styles.top_item}>
                            <TextLargeShared>{review.userName}님</TextLargeShared>
                            {isWriter && <ReviewDeleteBtn parentRefetch={parentRefetch} reviewId={review.reviewId} isWriter={isWriter} />}
                        </div>
                        <div className={styles.btm_item}>
                            <StarListShared star={review.star} />
                            <TextMediumShared>{review.createAt}</TextMediumShared>
                        </div>
                    </div>
                    <div className={styles.review_content}>
                        <TextLargeShared>{review.reviewTitle}</TextLargeShared>
                        <TextMediumShared>{review.reviewText}</TextMediumShared>
                    </div>
                </div>
            </ItemShared>
        </div>
    )
};

export default ReviewShared;