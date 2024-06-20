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
import { useRouter, useSearchParams } from "next/navigation";
import FormInputShared from "@/(FSD)/shareds/ui/FormInputShared";
import StarShared from "@/(FSD)/shareds/ui/StarShared";
import { useReviewUpdate } from "../api/useReviewUpdate";
import { useReviewRead } from "@/(FSD)/entities/review/api/useReviewRead";
import { ReviewType } from "@/(FSD)/shareds/types/Review.type";
import useUserStore from "@/(FSD)/shareds/stores/useUserStore";

const ReviewUpdateForm = () => {
    const searchParams = useSearchParams();
    const reviewId = +searchParams.get("reviewId")!;
    const { isLoggedIn } = useUserStore();
    const { data } = useReviewRead(Number(reviewId));

    const review: ReviewType = data;

    console.log(review);
    

    const [stars, setStars] = useState<Array<boolean>>([false, false, false, false, false]);

    const router = useRouter();

    const handleStarClick = (index: number) => {
        const newStars: Array<boolean> = stars.map((_, i) => i <= index);
        setStars(newStars);
    };

    const schema = z.object({
        reviewText: z.string().min(10).max(200),
    });

    const { control, handleSubmit, formState: { errors, isValid, submitCount } } = useForm({
        resolver: zodResolver(schema),
        mode: "onChange"
    });

    const onSuccess = (data: any) => {
        router.push(`/reply/create/${reviewId}`);
    }

    const [file, setFile] = useState<any>();

    const { mutate } = useReviewUpdate({ onSuccess });


    const onSubmit = (data: any) => {
        const formData = new FormData();

        formData.append("reviewUpdateDTO", JSON.stringify({ reviewText: data.reviewText, reviewId: Number(reviewId), star: stars.filter(star => star).length }));

        formData.append("files", file);

        mutate({ data: formData, reviewId: Number(reviewId) });
    }

    useEffect(() => {
        if(review) {
            setStars(Array.from({ length: 5 }, (_, i) => i < review.star));
        }
    }, [review]);

    if(!review) return <></>;
    if(!isLoggedIn) return <></>;
    if(!review.review) return <></>;
    
    return (
        <form className={styles.form} onSubmit={handleSubmit(onSubmit)}>
            <InnerShared>
                <div className={styles.star_list}>
                    <div className={styles.star_list_inner}>
                        {
                            stars.map((star, index) => (
                                <React.Fragment key={index}>
                                    <button type={"button"} onClick={_ => handleStarClick(index)}><StarShared isActive={star} /></button>
                                </React.Fragment>
                            ))
                        }
                    </div>
                </div>
                <div className={styles.review_input_box}>
                    <TextLargeShared>상품 이름 작성하기</TextLargeShared>
                    <FormInputShared placeholder={review.reviewTitle} isDisabled isInvalid={!!errors.reviewTitle} isClearable control={control} name={"reviewTitle"} />
                </div>
                <div className={styles.review_input_box}>
                    <TextLargeShared>리뷰 작성하기</TextLargeShared>
                    <FormTextareaShared placeholder={review.reviewText} isInvalid={!!errors.reviewText} size={"lg"} control={control} name={"reviewText"} />
                </div>
                <FileInputShared id={"review_img"} variant={"bordered"} setFile={setFile} fullWidth>이미지 업로드</FileInputShared>
                <Button isDisabled={!isValid} type={"submit"} color={"primary"} fullWidth size={"lg"}>수정하기</Button>
            </InnerShared>
        </form>
    )
}

export default ReviewUpdateForm;