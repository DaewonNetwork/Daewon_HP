"use client";

import React, { useReducer } from "react";
import { useUserReplyListRead } from "../api/useUserReplyListRead";
import { ReplyType } from "@/(FSD)/shareds/types/Reply.type";
import TextXlargeShared from "@/(FSD)/shareds/ui/TextXlargeShared";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import ReplyShared from "@/(FSD)/shareds/ui/ReplyShared";
import { Button } from "@nextui-org/button";
import styles from "@/(FSD)/shareds/styles/UserStyle.module.scss";
import TextMediumShared from "@/(FSD)/shareds/ui/TextMediumShared";

const UserReplyList = () => {
    const [isOpened, toggle] = useReducer((state) => !state, false);

    const { data } = useUserReplyListRead();

    const replyList: ReplyType[] = data;
    
    return (
        <div className={styles.user_writer_list}>
            <InnerShared>
                <TextXlargeShared>작성한 댓글</TextXlargeShared>
                <Button onClick={toggle} variant={"ghost"} fullWidth>
                    {isOpened ? "댓글 닫기" : "댓글 보기"}
                </Button>
            </InnerShared>
            {
                (isOpened && replyList && !replyList[0]) &&
                <div className={styles.no_writer_text}>
                    <TextMediumShared>작성하신 댓글이 없습니다.</TextMediumShared>
                </div>
            }
            {
                (isOpened && replyList) && replyList.map(reply => (
                    <React.Fragment key={reply.replyId}>
                        <ReplyShared reply={reply} isWriter={reply.reply} /> 
                    </React.Fragment>
                ))
            }
        </div>
    );
};

export default UserReplyList;