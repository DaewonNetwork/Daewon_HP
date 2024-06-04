import React from "react";
import { ReplyType } from "../types/Reply.type";
import styles from "@/(FSD)/shareds/styles/ReplyStyle.module.scss";
import TextLargeShared from "./TextLargeShared";
import TextMediumShared from "./TextMediumShared";
import ReplyDeleteBtn from "@/(FSD)/features/reply/ui/ReplyDeleteBtn";

const ReplyShared = ({ reply, parentRefetch }: { reply: ReplyType; parentRefetch?: any }) => {
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
                <ReplyDeleteBtn parentRefetch={parentRefetch} replyId={reply.replyId} isReply={reply.reply} />
            </div>
        </div>
    );
};

export default ReplyShared;