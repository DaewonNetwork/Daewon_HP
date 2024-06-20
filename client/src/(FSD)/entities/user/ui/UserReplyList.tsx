"use client";

import React from "react";
import { useUserReplyListRead } from "../api/useUserReplyListRead";
import { ReplyType } from "@/(FSD)/shareds/types/Reply.type";
import TextXlargeShared from "@/(FSD)/shareds/ui/TextXlargeShared";

const UserReplyList = () => {
    const { data } = useUserReplyListRead();

    const replyList: ReplyType[] = data;

    return (
        <div>
            <TextXlargeShared>작성한 댓글</TextXlargeShared>
        </div>
    );
};

export default UserReplyList;