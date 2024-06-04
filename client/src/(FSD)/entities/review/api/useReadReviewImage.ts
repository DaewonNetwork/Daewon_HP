import { useQuery } from "@tanstack/react-query";

const reviewImageReadFetch = async (reviewId: number) => {
    const response = await fetch(`http://localhost:8090/review/read/image?reviewId=${reviewId}`, {
        method: "GET",
        headers: {
            "Content-Type": "image/jpeg",
        }
    });

    const blob = await response.blob();
    
    if (blob.size) {
        console.log("aswdwddqwaqdwa");
        
        return blob;
    } else {
        throw new Error("errorMessage");
    }
};

export const useReadReviewImage = (reviewId: number) => {
    return useQuery({
        queryKey: ["review_image_read"],
        queryFn: _ => reviewImageReadFetch(reviewId)
    });
};