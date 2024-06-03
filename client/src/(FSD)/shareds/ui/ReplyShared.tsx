import React from "react";
import { ReplyType } from "../types/Reply.type";
import styles from "@/(FSD)/shareds/styles/ReplyStyle.module.scss";
import TextLargeShared from "./TextLargeShared";
import TextMediumShared from "./TextMediumShared";
import ReplyDeleteBtn from "@/(FSD)/entities/reply/ui/ReplyDeleteBtn";

const ReplyShared = ({ reply }: { reply: ReplyType}) => {
    return (
        <div className={styles.reply_item}>
            <div className={styles.left_box}>
                <TextLargeShared>{reply.userName}님</TextLargeShared>
            </div>
            <div className={styles.right_box}>
                <TextMediumShared>{reply.updateAt}</TextMediumShared>
                <TextMediumShared>{reply.replyText}</TextMediumShared>
            </div>
            <div>
                <ReplyDeleteBtn replyId={reply.reviewId} isReply={reply.reply} />
            </div>
        </div>
    );
};

export default ReplyShared;