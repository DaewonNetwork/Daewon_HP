"use client";

import React from "react";
import styles from "@/(FSD)/shareds/styles/AppStyle.module.scss";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";
import FormInputShared from "@/(FSD)/shareds/ui/FormInputShared";
import IconShared from "@/(FSD)/shareds/ui/IconShared";
import { Button } from "@nextui-org/button";
import { useParams, useRouter } from "next/navigation";

const PharmacyRegionKeywordForm = () => {
    const router = useRouter();
    const { city } = useParams<{ city: string }>();

    const schema = z.object({
        keyword: z.string().min(1).max(20)
    });

    const { control, handleSubmit, formState: { errors, isValid, submitCount } } = useForm({
        resolver: zodResolver(schema),
        mode: "onChange"
    });

    const onSubmit = (data: any) => {
        if(data.keyword) {
            router.push(`/pharmacy/region/search/${city}/${data.keyword}`); 
        }
    }

    return (
        <form className={styles.form} data-slot={"form"} onSubmit={handleSubmit(onSubmit)}>
            <FormInputShared isInvalid={!!errors.keyword} placeholder={"지역 내 약국"} size={"lg"} control={control} name={"keyword"} variant={"bordered"} color={"primary"} endContent={<Button isIconOnly size={"sm"} variant={"light"} className={"text-large"} type={"submit"}><IconShared iconType={"search"} /></Button>} />
        </form>
    )
}

export default PharmacyRegionKeywordForm;