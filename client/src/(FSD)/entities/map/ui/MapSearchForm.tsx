"use client";

import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";
import FormInputShared from "@/(FSD)/shareds/ui/FormInputShared";
import IconShared from "@/(FSD)/shareds/ui/IconShared";
import { Button } from "@nextui-org/button";

const MapSearchForm = () => {
    const schema = z.object({
        word: z.string().min(1).max(20)
    });

    const { control, handleSubmit, formState: { errors, isValid, submitCount } } = useForm({
        resolver: zodResolver(schema),
        mode: "onChange"
    });

    const onSubmit = (data: any) => {
        
    }

    return (
        <form className={""} onSubmit={handleSubmit(onSubmit)}>
            <FormInputShared isInvalid={!!errors.word} placeholder={"병원 이름 또는 지역"} size={"lg"} control={control} name={"word"} variant={"bordered"} color={"primary"} endContent={<Button isIconOnly size={"sm"} variant={"light"} className={"text-large"} type={"submit"}><IconShared iconType={"search"} /></Button>} />
        </form>
    )
}

export default MapSearchForm;