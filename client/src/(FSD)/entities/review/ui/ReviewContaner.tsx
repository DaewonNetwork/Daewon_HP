"use client";

import React, { useEffect } from "react";
import styles from "@/(FSD)/shareds/styles/ReviewStyle.module.scss";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import TextLargeShared from "@/(FSD)/shareds/ui/TextLargeShared";
import LinkBtnShared from "@/(FSD)/shareds/ui/LinkBtnShared";
import TextMediumShared from "@/(FSD)/shareds/ui/TextMediumShared";
import { useReviewListRead } from "@/(FSD)/entities/review/api/useReviewListRead";
import { useParams, useRouter } from "next/navigation";
import { ReviewType } from "@/(FSD)/shareds/types/Review.type";
import useUserStore from "@/(FSD)/shareds/stores/useUserStore";
import ReviewItem from "@/(FSD)/widgets/review/ui/ReviewItem";

const ReviewContaner = ({ parentRefetch }: { parentRefetch?: any }) => {
    const { phId } = useParams<{ phId: string; }>()!;

    const { isLoggedIn } = useUserStore();
    
    const { data, refetch } = useReviewListRead(+phId);
    
    const reviewList: ReviewType[] = data;

    const router = useRouter();

    useEffect(() => {
        refetch();
    }, [phId]);

    useEffect(() => {
        refetch();
    }, [router])


    if (!reviewList) return;

    return (
        <div className={styles.container}>
            <div className={styles.review_box}>
                <InnerShared>
                    <TextLargeShared>리뷰</TextLargeShared>
                    <LinkBtnShared href={isLoggedIn ? `/review/create?phId=${phId}` : "/auth/signin"} fullWidth size={"lg"} variant={"solid"} color={"primary"}><TextMediumShared>리뷰 작성하기</TextMediumShared></LinkBtnShared>
                </InnerShared>
            </div>
            <div className={styles.review_list}>
                {
                    reviewList.map(review => (
                        <React.Fragment key={review.reviewId}>
                            <ReviewItem isWriter={review.review} grandParentFetch={parentRefetch} parentRefetch={refetch} review={review} />
                        </React.Fragment>
                    ))
                }
            </div>
        </div>
    );
};

export default ReviewContaner;