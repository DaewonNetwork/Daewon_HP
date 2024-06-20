"use client";

import React from "react";
import { useUserReplyListRead } from "../api/useUserReplyListRead";
import { ReplyType } from "@/(FSD)/shareds/types/Reply.type";
import TextXlargeShared from "@/(FSD)/shareds/ui/TextXlargeShared";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import ReplyShared from "@/(FSD)/shareds/ui/ReplyShared";

const UserReplyList = () => {
    const { data } = useUserReplyListRead();

    const replyList: ReplyType[] = data;
    
    return (
        <div>
            <InnerShared><TextXlargeShared>작성한 댓글</TextXlargeShared></InnerShared>
            {
                replyList && replyList.map(reply => (
                    <React.Fragment key={reply.replyId}>
                        <ReplyShared reply={reply} isWriter={reply.reply} /> 
                    </React.Fragment>
                ))
            }
        </div>
    );
};

export default UserReplyList;