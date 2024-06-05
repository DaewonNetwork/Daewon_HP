import React, { useEffect, useState } from "react";
import styles from "@/(FSD)/shareds/styles/ReviewStyle.module.scss";
import type { ReviewType } from "../types/Review.type";
import { useRouter } from "next/navigation";
import ItemShared from "./ItemShared";
import TextLargeShared from "./TextLargeShared";
import StarListShared from "./StarListShared";
import TextMediumShared from "./TextMediumShared";
import ReplyShared from "./ReplyShared";
import { ReplyType } from "../types/Reply.type";
import MenuBarShared from "./MenuBarShared";
import { useDeleteReview } from "@/(FSD)/features/review/api/useDeleteReview";
import TextBoxShared from "./TextBoxShared";
import { DropdownItem } from "@nextui-org/dropdown";
import { useReadReplys } from "@/(FSD)/entities/reply/api/useReadReplys";

const ReviewShared = ({ review, parentRefetch, grandParentFetch, isWriter = false }: { review: ReviewType; parentRefetch?: any; grandParentFetch?: any; isWriter?: boolean }) => {
    const [isViewReply, setIsViewReply] = useState(true);
    const router = useRouter();

    if (!review) return <></>;

    const { data, refetch } = useReadReplys(review.reviewId);

    const replyList: ReplyType[] = data;

    useEffect(() => {
        refetch();
    }, [review]);

    const onSuccess = (data: any) => {
        if (parentRefetch) {
            parentRefetch();
        }

        if (grandParentFetch) {
            grandParentFetch();
        }
    };
    const { mutate } = useDeleteReview({ onSuccess });

    return (
        <div onClick={_ => router.push(`/reply/create/${review.reviewId}`)} className={styles.review_item}>
            <ItemShared>
                <div className={styles.review_inner}>
                    <div className={styles.review_header}>
                        <div className={styles.top_item}>
                            <TextLargeShared>{review.userName}님</TextLargeShared>
                            {isWriter &&
                                <div className={styles.writer_item}>
                                    <MenuBarShared path={`/review/update/${review.reviewId}`} mutate={mutate} id={review.reviewId}>
                                        <DropdownItem onClick={_ => setIsViewReply(!isViewReply)}>{isViewReply ? "답글 숨기기" : "답글 보기"}</DropdownItem>
                                    </MenuBarShared>
                                </div>
                            }
                        </div>
                        <div className={styles.btm_item}>
                            <StarListShared star={review.star} />
                            <TextMediumShared>{review.createAt}</TextMediumShared>
                        </div>
                    </div>
                    <div className={styles.review_content}>
                        <div className={styles.text_content}>
                            <TextLargeShared>{review.reviewTitle}</TextLargeShared>
                            <TextBoxShared><TextMediumShared>{review.reviewText}</TextMediumShared></TextBoxShared>
                        </div>
                        {review.reviewImage && (
                            <div className={styles.img_content}><img alt={"review_img"} src={`data:image/png;base64,${review.reviewImage}`} /></div>
                        )}
                    </div>
                </div>
                {
                    (isViewReply && replyList) && replyList.map((reply, index) => (
                        <React.Fragment key={index}>
                            <ReplyShared parentRefetch={parentRefetch} reply={reply} isWriter={reply.reply} />
                        </React.Fragment>
                    ))
                }
            </ItemShared>
        </div>
    );
};

export default ReviewShared;