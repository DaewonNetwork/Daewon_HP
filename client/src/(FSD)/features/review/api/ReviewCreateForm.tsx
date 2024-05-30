"use client";

import React, { useEffect, useState } from "react";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { useParams, useRouter } from "next/navigation";
import FormTextareaShared from "@/(FSD)/shareds/ui/FormTextareaShared";
import styles from "@/(FSD)/shareds/styles/ReviewStyle.module.scss";
import useUserStore from "@/(FSD)/shareds/stores/useUserStore";
import { Button } from "@nextui-org/button";
import IconShared from "@/(FSD)/shareds/ui/IconShared";

const ReviewCreateForm = () => {
    const { user } = useUserStore();
    const { phId } = useParams<{ phId: string }>();

    const [stars, setStars] = useState<Array<boolean>>([false, false, false, false, false]);
    const [starCount, setStarCount] = useState<number>(0);
    const schema = z.object({
        review_text: z.string().min(10).max(200)
    });

    const { control, handleSubmit, formState: { errors, isValid, submitCount } } = useForm({
        resolver: zodResolver(schema),
        mode: "onChange"
    });

    const router = useRouter();
    
    const handleStarClick = (index: number) => {
        const newStars = stars.map((_, i) => i <= index);
        setStars(newStars);
        setStarCount(stars.filter(star => star).length);
    };
    
    const onSubmit = (data: any) => {

    }


    useEffect(() => {
    }, [user]);

    if (!user) return <></>;

    return (
        <form className={styles.form} onSubmit={handleSubmit(onSubmit)}>
            <div className={styles.input_box}>
                <h2 className={"text-large font-semibold"}>별점</h2>
                {
                    stars.map((star, index) => (
                        <Button onClick={_ => handleStarClick(index)} className={star ? `${styles.star} ${styles.active}` : `${styles.star}`} size={"sm"} variant={"light"} isIconOnly endContent={<IconShared className={"text-xlarge"} iconType={"star"} />} />
                    ))
                }
            </div>
            <div className={styles.input_box}>
                <label htmlFor={"review_text"} className={"text-large font-semibold"}>작성하기</label>
                <FormTextareaShared isInvalid={!!errors.review_text} placeholder={"내용을 입력해주세요."} size={"lg"} control={control} name={"review_text"} variant={"bordered"} color={"primary"} />
            </div>
        </form>
    )
}

export default ReviewCreateForm;