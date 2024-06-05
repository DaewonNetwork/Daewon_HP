import React from "react";
import { ReplyType } from "../types/Reply.type";
import styles from "@/(FSD)/shareds/styles/ReplyStyle.module.scss";
import TextLargeShared from "./TextLargeShared";
import TextMediumShared from "./TextMediumShared";
import MenuBarShared from "./MenuBarShared";
import { useDeleteReply } from "@/(FSD)/features/reply/api/useDeleteReply";
import TextBoxShared from "./TextBoxShared";

const ReplyShared = ({ reply, parentRefetch, isWriter = false }: { reply: ReplyType; parentRefetch?: any; isWriter?: boolean }) => {
    const onSuccess = (data: any) => {
        if (parentRefetch) {
            parentRefetch();
        }
    }

    const { mutate } = useDeleteReply({ onSuccess });

    return (
        <div className={styles.reply_item}>
            <div className={styles.item_inner}>
                <div className={styles.top_item}>
                    <TextLargeShared>{reply.userName}님</TextLargeShared>
                    <TextMediumShared>{reply.updateAt}</TextMediumShared>
                </div>
                <div className={styles.btm_item}>
                    <TextBoxShared isBgColor={true}><TextMediumShared>{reply.replyText}</TextMediumShared></TextBoxShared>
                </div>
            </div>
            <div className={styles.writer_item}>
                {isWriter && <MenuBarShared path={`/reply/update/${reply.replyId}`} mutate={mutate} id={reply.replyId} />}
            </div>
        </div>
    );
};

export default ReplyShared;