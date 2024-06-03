import IconShared from "@/(FSD)/shareds/ui/IconShared";
import { Button } from "@nextui-org/button";
import React from "react";
import { useDeleteReview } from "../api/useDeleteReview";

const ReviewDeleteBtn = ({ isReview, reviewId, parentRefetch }: { isReview: boolean; reviewId: number; parentRefetch: any; }) => {
    console.log(isReview);
    
    const onSuccess = (data: any) => {
        if(parentRefetch) {
            parentRefetch()
        }
    };
    const { mutate } = useDeleteReview({ onSuccess });
    if (!isReview) return <></>;


    return (
        <Button onClick={_ => mutate(reviewId)} size={"md"} variant={"light"} isIconOnly endContent={<IconShared iconType={"close"} />} />
    )
}

export default ReviewDeleteBtn;