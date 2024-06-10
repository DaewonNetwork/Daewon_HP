"use client";

import React from "react";
import UserLogoutButton from "@/(FSD)/features/user/ui/UserLogoutButton";
import FooterShared from "@/(FSD)/shareds/ui/FooterShared";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import { useUserReviewsRead } from "@/(FSD)/entities/review/api/useReadMyReview";
import { ReviewType } from "@/(FSD)/shareds/types/Review.type";
import ReviewShared from "@/(FSD)/shareds/ui/ReviewShared";
import TextLargeShared from "@/(FSD)/shareds/ui/TextLargeShared";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import TitleHeader from "@/(FSD)/widgets/app/ui/TitleHeader";
import { ReplyType } from "@/(FSD)/shareds/types/Reply.type";
import { useUserReplysRead } from "@/(FSD)/entities/reply/api/useUserReplysRead";
import ReplyShared from "@/(FSD)/shareds/ui/ReplyShared";

const Page = () => {
    const reviewList: ReviewType[] = useUserReviewsRead().data;
    const replyList: ReplyType[] = useUserReplysRead().data;

    console.log(replyList);
    

    return (
        <>
        <HeaderShared>
            <TitleHeader title={"프로필"} />
        </HeaderShared>
            <SectionShared>
                <InnerShared>
                    <TextLargeShared>작성한 리뷰</TextLargeShared>
                    {
                        reviewList && reviewList.map((review, index) => (
                            <React.Fragment key={index}>
                                <ReviewShared review={review} isWriter={review.review} />
                            </React.Fragment>
                        ))
                    }
                    <TextLargeShared>작성한 댓글</TextLargeShared>
                    {
                        replyList && replyList.map((reply, index) => (
                            <React.Fragment key={index}>
                                <ReplyShared reply={reply} isWriter={reply.reply} />
                            </React.Fragment>
                        ))
                    }
                    <UserLogoutButton />
                </InnerShared>
            </SectionShared>
            <FooterShared>
                <AppFooter />
            </FooterShared>
        </>
    );
};

export default Page;