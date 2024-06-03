"use client";

import React, { useState } from "react";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";
import FormTextareaShared from "@/(FSD)/shareds/ui/FormTextareaShared";
import { Button } from "@nextui-org/button";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import TextLargeShared from "@/(FSD)/shareds/ui/TextLargeShared";
import { useParams } from "next/navigation";
import { useReplyCreate } from "../api/useReplyCreate";

const ReplyCreateForm = () => {
    const { reviewId } = useParams<{ reviewId: string }>();

    const schema = z.object({
        replyText: z.string().min(10).max(200)
    });

    const { control, handleSubmit, formState: { errors, isValid, submitCount } } = useForm({
        resolver: zodResolver(schema),
        mode: "onChange"
    });

    const onSuccess = (data: any) => {
        console.log(data);
    }

    const { mutate } = useReplyCreate({ onSuccess });

    const onSubmit = (data: any) => {
        mutate({ reviewId, replyText: data.replyText });
    }
    

    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <InnerShared>
                <TextLargeShared>댓글 작성하기</TextLargeShared>
                <FormTextareaShared isInvalid={!!errors.replyText} size={"lg"} control={control} name="replyText" placeholder="10자 이상 200자 이하" />
                <Button isDisabled={!isValid} type={"submit"} color={"primary"} fullWidth size={"lg"}>댓글 등록</Button>
            </InnerShared>
        </form>
    )
}

export default ReplyCreateForm;