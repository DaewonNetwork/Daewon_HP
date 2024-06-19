"use client";

import React from "react";
import UserLogoutButton from "@/(FSD)/features/user/ui/UserLogoutButton";
import FooterShared from "@/(FSD)/shareds/ui/FooterShared";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import { ReviewType } from "@/(FSD)/shareds/types/Review.type";
import ReviewShared from "@/(FSD)/shareds/ui/ReviewShared";
import TextLargeShared from "@/(FSD)/shareds/ui/TextLargeShared";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import TitleHeader from "@/(FSD)/widgets/app/ui/TitleHeader";
import { ReplyType } from "@/(FSD)/shareds/types/Reply.type";
import ReplyShared from "@/(FSD)/shareds/ui/ReplyShared";
import { useUserReviewListRead } from "@/(FSD)/entities/user/api/useUserReviewListRead";
import { useUserReplyListRead } from "@/(FSD)/entities/user/api/useUserReplyListRead";

const Page = () => {
    const reviewList: ReviewType[] = useUserReviewListRead().data;
    const replyList: ReplyType[] = useUserReplyListRead().data;
    

    return (
        <>
        <HeaderShared>
            <TitleHeader title={"프로필"} />
        </HeaderShared>
            <SectionShared>
                <InnerShared>
                    <TextLargeShared>작성한 리뷰</TextLargeShared>
                    {
                        reviewList && reviewList.map(review => (
                            <React.Fragment key={review.reviewId}>
                                <ReviewShared review={review} isWriter={review.review} />
                            </React.Fragment>
                        ))
                    }
                    <TextLargeShared>작성한 댓글</TextLargeShared>
                    {
                        replyList && replyList.map(reply => (
                            <React.Fragment key={reply.replyId}>
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