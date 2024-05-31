import { useQuery } from "@tanstack/react-query";

const reviewReadFetch = async (phId: number) => {
    const response = await fetch(`http://localhost:8090/review?phId=${phId}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    });

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    };

    const data = await response.json();
    
    return data;
};

export const useReadReview = (phId: number) => {
    return useQuery({
        queryKey: ["review_read"],
        queryFn: _ => reviewReadFetch(phId)
    });
};