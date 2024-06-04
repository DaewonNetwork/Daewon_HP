import { useQuery } from "@tanstack/react-query";

const reviewImageReadFetch = async (reviewId: number) => {
    const response = await fetch(`http://localhost:8090/review/read/image?reviewId=${reviewId}`, {
        method: "GET",
    });

    if (!response.ok) {
        throw new Error("Network response was not ok");
    }

    const contentType = response.headers.get("content-type");
    
    if (contentType && contentType.includes("image")) {
        const blob = await response.blob();
        console.log(blob);
        
        return URL.createObjectURL(blob);
    } else {
        throw new Error("Response is not an image");
    }
};

export const useReadReviewImage = (reviewId: number) => {
    return useQuery({
        queryKey: ["review_image_read"],
        queryFn: _ => reviewImageReadFetch(reviewId)
    });
};