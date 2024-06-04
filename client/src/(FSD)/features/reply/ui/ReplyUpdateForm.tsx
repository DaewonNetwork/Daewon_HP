"use client";

import React from "react";
import { useReplyUpdate } from "../api/useReplyUpdate";
import { zodResolver } from "@hookform/resolvers/zod";
import { useParams } from "next/navigation";
import { z } from "zod";
import { useForm } from "react-hook-form";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import FormTextareaShared from "@/(FSD)/shareds/ui/FormTextareaShared";
import { Button } from "@nextui-org/button";
import styles from "@/(FSD)/shareds/styles/ReplyStyle.module.scss";

const ReplyUpdateForm = () => {
    const { replyId } = useParams<{ replyId: string }>();

    const schema = z.object({
        replyText: z.string().min(10).max(200)
    });
    
    const { control, handleSubmit, formState: { errors, isValid, submitCount } } = useForm({
        resolver: zodResolver(schema),
        mode: "onChange"
    });
    
    const onSuccess = (data: any) => {

    }
    
    const { mutate } = useReplyUpdate({ onSuccess });
    
    const onSubmit = (data: any) => {
        mutate({ replyId: Number(replyId), replyText: data.replyText });
    }

    return (
        <form className={styles.reply_form} onSubmit={handleSubmit(onSubmit)}>
            <InnerShared>
                <FormTextareaShared isInvalid={!!errors.replyText} size={"lg"} control={control} name="replyText" placeholder="10자 이상 200자 이하" />
                <Button isDisabled={!isValid} type={"submit"} color={"primary"} fullWidth size={"lg"}>수정하기</Button>
            </InnerShared>
        </form>
    );
};

export default ReplyUpdateForm;