"use client";

import React from "react";
import type { ReviewType } from "../types/reviews/Review.type";
import IconShared from "./IconShared";
import { useRouter } from "next/navigation";

const ReviewShared = ({ review }: { review: ReviewType }) => {

    const router = useRouter();
    if(!review) return <></>;
    
    return (
        <>
        </>
    )
};

export default ReviewShared;