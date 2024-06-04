import { useQuery } from "@tanstack/react-query";

const reviewImageReadFetch = async (reviewId: number) => {
    const response = await fetch(`http://localhost:8090/review/read/image?reviewId=${reviewId}`, {
        method: "GET",
    });

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    };

    const data = await response.json();
    
    return data;
};

export const useReadReviewImage = (reviewId: number) => {
    return useQuery({
        queryKey: ["review_image_read"],
        queryFn: _ => reviewImageReadFetch(reviewId)
    });
};