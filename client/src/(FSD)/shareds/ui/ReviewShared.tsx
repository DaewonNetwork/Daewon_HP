"use client";

import React from "react";
import styles from "@/(FSD)/shareds/styles/ReviewStyle.module.scss";
import type { ReviewType } from "../types/reviews/Review.type";
import IconShared from "./IconShared";
import { useRouter } from "next/navigation";
import ItemShared from "./ItemShared";
import TextLargeShared from "./TextLargeShared";
import TextMediumShared from "./TextMediumShared";
import StarShared from "./StarShared";

const ReviewShared = ({ review }: { review: ReviewType }) => {
    const router = useRouter();

    if (!review) return <></>;

    const starList = Array.from({ length: 5 }, (_, i) => i < review.star);
    

    return (
        <div onClick={_ => router.push} className={styles.review_item}>
            <ItemShared>
                <TextLargeShared>{review.userName}님</TextLargeShared>
                {
                    starList.map((star, index) => (
                        <React.Fragment key={index}>
                            <StarShared isActive={star} />
                        </React.Fragment>
                    ))
                }
            </ItemShared>
        </div>
    )
};

export default ReviewShared;