"use client";

import React from "react";
import styles from "@/(FSD)/shareds/styles/ReviewStyle.module.scss";
import type { ReviewType } from "../types/reviews/Review.type";
import { useRouter } from "next/navigation";
import ItemShared from "./ItemShared";
import TextLargeShared from "./TextLargeShared";
import StarListShared from "./StarListShared";

const ReviewShared = ({ review }: { review: ReviewType }) => {
    const router = useRouter();

    if (!review) return <></>;
    

    return (
        <div onClick={_ => router.push} className={styles.review_item}>
            <ItemShared>
                <TextLargeShared>{review.userName}님</TextLargeShared>
                <StarListShared star={review.star} />
            </ItemShared>
        </div>
    )
};

export default ReviewShared;