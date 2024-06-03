"use client";

import React, { useEffect, useState } from "react";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";
import FormTextareaShared from "@/(FSD)/shareds/ui/FormTextareaShared";
import { Button } from "@nextui-org/button";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import TextLargeShared from "@/(FSD)/shareds/ui/TextLargeShared";
import styles from "@/(FSD)/shareds/styles/ReviewStyle.module.scss";
import FileInputShared from "@/(FSD)/shareds/ui/FileInputShared";
import { useReviewCreate } from "../api/useReviewCreate";
import { useParams } from "next/navigation";
import FormInputShared from "@/(FSD)/shareds/ui/FormInputShared";
import StarListShared from "@/(FSD)/shareds/ui/StarListShared";
import IconShared from "@/(FSD)/shareds/ui/IconShared";
import StarShared from "@/(FSD)/shareds/ui/StarShared";

const ReviewCreateForm = () => {
    const [stars, setStars] = useState<Array<boolean>>([false, false, false, false, false]);

    const handleStarClick = (index: number) => {
        const newStars: Array<boolean> = stars.map((_, i) => i <= index);
        setStars(newStars);
    };

    const { phId } = useParams<{ phId: string }>();

    const schema = z.object({
        reviewText: z.string().min(10).max(200),
        reviewTitle: z.string().min(1).max(20)
    });

    const { control, handleSubmit, formState: { errors, isValid, submitCount } } = useForm({
        resolver: zodResolver(schema),
        mode: "onChange"
    });

    const onSuccess = (data: any) => {
        console.log(data);
    }

    const [file, setFile] = useState<any>();

    const { mutate } = useReviewCreate({ onSuccess });


    const onSubmit = (data: any) => {
        const formData = new FormData();

        formData.append("reviewDTO", JSON.stringify({ reviewText: data.reviewText, reviewTitle: data.reviewTitle, phId: Number(phId), star: stars.filter(star => star).length }));

        formData.append("files", file);

        console.log(formData.get("files"));
        console.log(formData.get("reviewDTO"));

        mutate(formData);
    }

    return (
        <form className={styles.form} onSubmit={handleSubmit(onSubmit)}>
            <InnerShared>
                {
                    stars.map((star, index) => (
                        <React.Fragment key={index}>
                            <button type={"button"} onClick={_ => handleStarClick(index)}><StarShared isActive={star} /></button>
                        </React.Fragment>
                    ))
                }
                <TextLargeShared>상품 이름 작성하기</TextLargeShared>
                <FormInputShared isInvalid={!!errors.reviewTitle} isClearable control={control} name={"reviewTitle"} placeholder={"구매한 상품 이름을 입력하세요."} />
                <TextLargeShared>리뷰 작성하기</TextLargeShared>
                <FormTextareaShared isInvalid={!!errors.reviewText} size={"lg"} control={control} name="reviewText" placeholder="10자 이상 200자 이하" />
                <FileInputShared id={"review_img"} variant={"bordered"} setFile={setFile} fullWidth>이미지 업로드</FileInputShared>
                <Button isDisabled={!isValid} type={"submit"} color={"primary"} fullWidth size={"lg"}>리뷰 등록</Button>
            </InnerShared>
        </form>
    )
}

export default ReviewCreateForm;