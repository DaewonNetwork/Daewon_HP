import { useQuery } from "@tanstack/react-query";

const reviewImageReadFetch = async (reviewId: number) => {
    const response = await fetch(`http://localhost:8090/api/review/read/image?reviewId=${reviewId}`, {
        method: "GET",
        headers: {
            "Content-Type": "image/jpeg",
            Authorization: `Bearer ${localStorage.getItem("access_token") || ""}`
        }
    });

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    };

    const blob = await response.blob();

    if (blob.size) {
        return URL.createObjectURL(blob);
    } else {
        return null;
    }
};

export const useReadReviewImage = (reviewId: number) => {
    return useQuery({
        queryKey: ["review_image_read"],
        queryFn: _ => reviewImageReadFetch(reviewId),
        refetchOnMount: true
    });
};