import React from "react";
import type { ReviewType } from "../types/reviews/Review.type";
import styles from "@/(FSD)/shareds/styles/ReviewStyle.module.scss";
import IconShared from "./IconShared";

const ReviewShared = ({ review }: { review: ReviewType }) => {

    if(!review) return <></>;
    

    return (
        <div className={styles.review_item}>
            <div className={styles.review_header}>
                <div className={styles.review_top_box}>
                    <h2 className={"text-large"}>{review.star}</h2>
                </div>
                <div className={styles.review_btm_box}>
                    <p className={"text-medium"}><IconShared iconType={"star"} /> {review.star}</p>
                    <p>{review.updateAt}</p>
                </div>
            </div>
            <div className={styles.review_body}>
                <p className={`bg-content3 rounded-medium text-medium ${styles.review_text}`}>{review.reviewText}</p>
            </div>
        </div>
    )
};

export default ReviewShared;