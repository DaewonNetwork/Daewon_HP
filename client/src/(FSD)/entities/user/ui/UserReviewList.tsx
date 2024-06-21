"use client";

import React, { useReducer } from "react";
import { useUserReviewListRead } from "../api/useUserReviewListRead";
import { ReviewType } from "@/(FSD)/shareds/types/Review.type";
import ReviewItem from "@/(FSD)/widgets/review/ui/ReviewItem";
import TextXlargeShared from "@/(FSD)/shareds/ui/TextXlargeShared";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import { Button } from "@nextui-org/button";
import styles from "@/(FSD)/shareds/styles/UserStyle.module.scss";
import TextMediumShared from "@/(FSD)/shareds/ui/TextMediumShared";

const UserReviewList = () => {
    const [isOpened, toggle] = useReducer((state) => !state, false);

    const { data } = useUserReviewListRead();

    const reviewList: ReviewType[] = data;

    return (
        <div className={styles.user_writer_list}>
            <InnerShared>
                <TextXlargeShared>작성한 리뷰</TextXlargeShared>
                <Button onClick={toggle} variant={"ghost"} fullWidth>
                    {isOpened ? "리뷰 닫기" : "리뷰 보기"}
                </Button>
            </InnerShared>
            {
                (isOpened && reviewList && !reviewList[0]) &&
                <div className={styles.no_writer_text}>
                    <TextMediumShared>작성하신 리뷰가 없습니다.</TextMediumShared>
                </div>
            }

            {
                (isOpened && reviewList) && reviewList.map(review => (
                    <React.Fragment key={review.reviewId}>
                        <ReviewItem review={review} isWriter={review.review} />
                    </React.Fragment>
                ))
            }
        </div>
    );
};

export default UserReviewList;