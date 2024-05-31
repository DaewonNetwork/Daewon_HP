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
import { useReviewCreate } from "../api/useReviewCreate";
import FileInputShared from "@/(FSD)/shareds/ui/FileInputShared";

const ReviewCreateForm = () => {
    const { user } = useUserStore();
    const { phId } = useParams<{ phId: string }>();

    const onSuccess = (data: any) => { 
        router.push(`/pharmacy/${phId}`);
    };

    const { mutate } = useReviewCreate({ onSuccess });

    const [stars, setStars] = useState<Array<boolean>>([false, false, false, false, false]);
    const [reviewFile, setReviewFile] = useState<any>();

    const schema = z.object({
        reviewText: z.string().min(10).max(200)
    });

    const { control, handleSubmit, formState: { errors, isValid, submitCount } } = useForm({
        resolver: zodResolver(schema),
        mode: "onChange"
    });

    const router = useRouter();

    const handleStarClick = (index: number) => {
        const newStars = stars.map((_, i) => i <= index);
        setStars(newStars);
    };

    const onSubmit = (data: any) => {
        const formData = new FormData();
        
        if (!phId) return;
        if (!data.reviewText) return;
        if (!user?.userId) return;

        console.log(user.userId);
        

        formData.append("reviewDTO", JSON.stringify({ reviewText: data.reviewText, star: stars.filter(star => star).length, phId: phId, userId: user.userId }));
        formData.append("files", JSON.stringify(reviewFile?.files[0] || null));
        mutate(formData);
    }


    useEffect(() => {
    }, [user]);

    if (!user) return <></>;

    return (
        <form className={styles.form} onSubmit={handleSubmit(onSubmit)}>
            <div className={styles.input_box}>
                <h2 className={"text-medium font-medium"}>별점</h2>
                <div className={styles.star_box}>
                    {
                        stars.map((star, index) => (
                            <React.Fragment key={index}>
                                <Button onClick={_ => handleStarClick(index)} className={star ? `${styles.star} ${styles.active}` : `${styles.star}`} size={"sm"} variant={"light"} isIconOnly endContent={<IconShared className={"text-xlarge"} iconType={"star"} />} />
                            </React.Fragment>
                        ))
                    }
                </div>
            </div>
            <div className={styles.input_box}>
                <label htmlFor={"reviewText"} className={"text-medium font-medium"}>작성하기</label>
                <FormTextareaShared isInvalid={!!errors.reviewText} placeholder={"내용을 입력해주세요."} size={"lg"} control={control} name={"reviewText"} variant={"bordered"} color={"primary"} />
                <FileInputShared setFile={setReviewFile} variant={"ghost"} id={"review_image"} size={"md"} fullWidth>이미지 업로드</FileInputShared>
                <Button fullWidth type={"submit"} size={"lg"} isDisabled={!isValid} color={"primary"}>등록하기</Button>
            </div>
        </form>
    )
}

export default ReviewCreateForm;