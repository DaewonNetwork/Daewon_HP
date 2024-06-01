"use client";

import React from "react";
import styles from "@/(FSD)/shareds/styles/AppStyle.module.scss";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";
import FormInputShared from "@/(FSD)/shareds/ui/FormInputShared";
import IconShared from "@/(FSD)/shareds/ui/IconShared";
import { Button } from "@nextui-org/button";
import { useRouter } from "next/navigation";

const MapSearchForm = () => {
    const schema = z.object({
        keyword: z.string().min(1).max(20)
    });

    const { control, handleSubmit, formState: { errors, isValid, submitCount } } = useForm({
        resolver: zodResolver(schema),
        mode: "onChange"
    });

    const router = useRouter();

    const onSubmit = (data: any) => {
        if(data.keyword) {
            router.push(`/map/search/${data.keyword}`); 
        }
    }

    return (
        <form className={styles.form} data-slot={"form"} onSubmit={handleSubmit(onSubmit)}>
            <FormInputShared isInvalid={!!errors.keyword} placeholder={"약국 이름 또는 지역"} size={"lg"} control={control} name={"keyword"} variant={"bordered"} color={"primary"} endContent={<Button isIconOnly size={"sm"} variant={"light"} className={"text-large"} type={"submit"}><IconShared iconType={"search"} /></Button>} />
        </form>
    )
}

export default MapSearchForm;