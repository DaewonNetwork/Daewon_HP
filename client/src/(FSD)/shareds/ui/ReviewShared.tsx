"use client";

import React, { useEffect } from "react";
import styles from "@/(FSD)/shareds/styles/ReviewStyle.module.scss";
import type { ReviewType } from "../types/Review.type";
import { useRouter } from "next/navigation";
import ItemShared from "./ItemShared";
import TextLargeShared from "./TextLargeShared";
import StarListShared from "./StarListShared";
import TextMediumShared from "./TextMediumShared";
import { useReadReply } from "@/(FSD)/entities/reply/api/useReadReply";
import ReplyShared from "./ReplyShared";
import { ReplyType } from "../types/Reply.type";
import ReviewDeleteBtn from "@/(FSD)/entities/review/ui/ReviewDeleteBtn";

const ReviewShared = ({ review, parentRefetch, isDeleteBtn = false }: { review: ReviewType; parentRefetch?: any; isDeleteBtn?: boolean }) => {
    const router = useRouter();

    if (!review) return <></>;
    
    const { data, refetch } = useReadReply(review.reviewId);
    
    const replyList: ReplyType[] = data;
    
    useEffect(() => {
        refetch();
    }, [review]);


    return (
        <div onClick={_ => router.push(`/reply/create/${review.reviewId}`)} className={styles.review_item}>
            <ItemShared>
                <div className="">
                    <div className={styles.review_header}>
                        <div className={styles.top_item}>
                            <TextLargeShared>{review.userName}님</TextLargeShared>
                            {isDeleteBtn && <ReviewDeleteBtn parentRefetch={parentRefetch} reviewId={review.reviewId} isReview={review.review} />}
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
                {
                    replyList && replyList.map((reply, index) => (
                        <React.Fragment key={index}>
                            <ReplyShared reply={reply} />
                        </React.Fragment>
                    ))
                }
            </ItemShared>
        </div>
    )
};

export default ReviewShared;