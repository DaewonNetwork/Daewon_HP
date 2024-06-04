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
import ReviewDeleteBtn from "@/(FSD)/features/review/ui/ReviewDeleteBtn";


const ReviewShared = ({ review, parentRefetch, grandParentFetch, isWriter = false }: { review: ReviewType; parentRefetch?: any; grandParentFetch?: any; isWriter?: boolean }) => {
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
                <div className={styles.review_inner}>
                    <div className={styles.review_header}>
                        <div className={styles.top_item}>
                            <TextLargeShared>{review.userName}님</TextLargeShared>
                            {isWriter &&
                                <div className={styles.writer_item}>
                                    <ReviewDeleteBtn parentRefetch={parentRefetch} grandParentFetch={grandParentFetch} reviewId={review.reviewId} isWriter={review.review} />
                                </div>
                            }
                        </div>
                        <div className={styles.btm_item}>
                            <StarListShared star={review.star} />
                            <TextMediumShared>{review.createAt}</TextMediumShared>
                        </div>
                    </div>
                    <div className={styles.review_content}>
                        <TextLargeShared>{review.reviewTitle}</TextLargeShared>
                        <TextMediumShared>{review.reviewText}</TextMediumShared>
                        {/* 이미지 렌더링 */}
                        {review.reviewImage && (
                            <img
                                style={{ width: 100, height: 100 }}
                                src={`data:image/png;base64,${review.reviewImage}`}
                                alt=""
                            />
                        )}
                    </div>
                </div>
                {
                    replyList && replyList.map((reply, index) => (
                        <React.Fragment key={index}>
                            <ReplyShared parentRefetch={parentRefetch} reply={reply} />
                        </React.Fragment>
                    ))
                }
            </ItemShared>
        </div>
    );
};

export default ReviewShared;
