"use client";

import { useParams } from "next/navigation";
import React, { useEffect } from "react";
import { useReadReviews } from "../api/useReadReviews";

const ReviewList = () => {
    const { phId } = useParams<{ phId: string }>();
    
    const { data, isError, isLoading, refetch } = useReadReviews(Number(phId));

    console.log(data);
    

    useEffect(() => {
        refetch();
    }, [phId]);

    return (
        <div>ReviewList</div>
    );
};

export default ReviewList;