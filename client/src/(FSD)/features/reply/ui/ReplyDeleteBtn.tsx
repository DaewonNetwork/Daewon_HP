import IconShared from "@/(FSD)/shareds/ui/IconShared";
import { Button } from "@nextui-org/button";
import React from "react";
import { useDeleteReply } from "../api/useDeleteReply";

const ReplyDeleteBtn = ({ isReply, replyId, parentRefetch }: { isReply: boolean; replyId: number; parentRefetch?: any; }) => {
    const onSuccess = (data: any) => {
        if(parentRefetch) {
            parentRefetch()
        }
    };
    const { mutate } = useDeleteReply({ onSuccess });
    if (!isReply) return <></>;


    return (
        <Button onClick={_ => mutate(replyId)} size={"md"} variant={"light"} isIconOnly endContent={<IconShared iconType={"close"} />} />
    )
}

export default ReplyDeleteBtn;