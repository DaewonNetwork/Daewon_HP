"use client";

import React, { useEffect } from "react";
import styles from "@/(FSD)/shareds/styles/ReviewStyle.module.scss";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import TextLargeShared from "@/(FSD)/shareds/ui/TextLargeShared";
import LinkBtnShared from "@/(FSD)/shareds/ui/LinkBtnShared";
import TextMediumShared from "@/(FSD)/shareds/ui/TextMediumShared";
import { useReviewsRead } from "@/(FSD)/entities/review/api/useReviewsRead";
import { useRouter, useSearchParams } from "next/navigation";
import ReviewShared from "@/(FSD)/shareds/ui/ReviewShared";
import { ReviewType } from "@/(FSD)/shareds/types/Review.type";

const ReviewContaner = ({ parentRefetch }: { parentRefetch?: any }) => {
    const searchParams = useSearchParams();
    const phId = +searchParams.get("phId")!;

    const { data, refetch } = useReviewsRead(phId);
    
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
                    {!!localStorage.getItem("access_token") && <LinkBtnShared href={`/review/create?phId=${phId}`} fullWidth size={"lg"} variant={"solid"} color={"primary"}><TextMediumShared>리뷰 작성하기</TextMediumShared></LinkBtnShared>}
                </InnerShared>
            </div>
            <div className={styles.review_list}>
                {
                    reviewList.map((review, index) => (
                        <React.Fragment key={index}>
                            <ReviewShared isWriter={review.review} grandParentFetch={parentRefetch} parentRefetch={refetch} review={review} />
                        </React.Fragment>
                    ))
                }
            </div>
        </div>
    );
};

export default ReviewContaner;