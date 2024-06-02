"use client";

import React from "react";
import styles from "@/(FSD)/shareds/styles/ReviewStyle.module.scss";
import type { ReviewType } from "../types/reviews/Review.type";
import { useRouter } from "next/navigation";
import ItemShared from "./ItemShared";
import TextLargeShared from "./TextLargeShared";
import StarListShared from "./StarListShared";
import TextMediumShared from "./TextMediumShared";

const ReviewShared = ({ review }: { review: ReviewType }) => {
    const router = useRouter();

    if (!review) return <></>;


    return (
        <div onClick={_ => router.push(`/reply/${review.reviewId}`)} className={styles.review_item}>
            <ItemShared>
                <div className={styles.review_header}>
                    <div className={styles.top_item}>
                        <TextLargeShared>{review.userName}님</TextLargeShared>
                    </div>
                    <div className={styles.btm_item}>
                        <StarListShared star={review.star} />
                        <TextMediumShared>{review.createAt}</TextMediumShared>
                    </div>
                </div>
                <div className={styles.review_content}>
                    <TextMediumShared>{review.reviewText}</TextMediumShared>
                </div>
            </ItemShared>
        </div>
    )
};

export default ReviewShared;