"use client";

import React, { useEffect, useMemo } from "react";
import styles from "@/(FSD)/shareds/styles/ReviewStyle.module.scss";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import TextLargeShared from "@/(FSD)/shareds/ui/TextLargeShared";
import LinkBtnShared from "@/(FSD)/shareds/ui/LinkBtnShared";
import TextMediumShared from "@/(FSD)/shareds/ui/TextMediumShared";
import { useReadReviews } from "@/(FSD)/entities/review/api/useReadReviews";
import { useParams } from "next/navigation";
import ReviewShared from "@/(FSD)/shareds/ui/ReviewShared";
import { ReviewType } from "@/(FSD)/shareds/types/Review.type";
import ReviewImage from "@/(FSD)/widgets/review/ui/ReviewImage";

const ReviewContaner = () => {
    const { phId } = useParams<{ phId: string }>();

    const { data, refetch } = useReadReviews(Number(phId));
    
    const reviewList: ReviewType[] = data;

    useEffect(() => {
        refetch();
    }, [phId]);

    if (!reviewList) return;

    return (
        <div className={styles.container}>
            <div className={styles.review_box}>
                <InnerShared>
                    <TextLargeShared>리뷰</TextLargeShared>
                    <LinkBtnShared href={`/review/create/${phId}`} fullWidth size={"lg"} variant={"solid"} color={"primary"}><TextMediumShared>리뷰 작성하기</TextMediumShared></LinkBtnShared>
                </InnerShared>
            </div>
            <div className={styles.review_list}>
                {
                    reviewList.map((review, index) => (
                        <React.Fragment key={index}>
                            <ReviewShared review={review} parentRefetch={refetch} isWriter={review.review} />
                            <ReviewImage review={review} />
                        </React.Fragment>
                    ))
                }
            </div>
        </div>
    );
};

export default ReviewContaner;