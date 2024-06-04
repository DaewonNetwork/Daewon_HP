import { useDeleteReview } from "@/(FSD)/features/review/api/useDeleteReview";
import IconShared from "@/(FSD)/shareds/ui/IconShared";
import { Button } from "@nextui-org/button";
import React from "react";

const ReviewDeleteBtn = ({ isWriter, reviewId, parentRefetch, grandParentFetch }: { isWriter: boolean; reviewId: number; parentRefetch: any; grandParentFetch?: any }) => {
    const onSuccess = (data: any) => {
        if(parentRefetch) {
            parentRefetch();
            grandParentFetch();
        }
    };
    const { mutate } = useDeleteReview({ onSuccess });
    if (!isWriter) return <></>;


    return (
        <Button onClick={_ => mutate(reviewId)} size={"md"} variant={"light"} isIconOnly endContent={<IconShared iconType={"close"} />} />
    )
}

export default ReviewDeleteBtn;