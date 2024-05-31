import { useQuery } from "@tanstack/react-query";

const reviewsReadFetch = async (phId: number) => {
    const response = await fetch(`http://localhost:8090/review/list?phId=${phId}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    });

    const data = await response.json();
    
    return data;
};

export const useReadReviews = (phId: number) => {
    return useQuery({
        queryKey: ["reviews_read"],
        queryFn: _ => reviewsReadFetch(phId)
    });
};