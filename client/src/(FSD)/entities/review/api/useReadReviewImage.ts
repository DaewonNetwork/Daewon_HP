import { useQuery } from "@tanstack/react-query";

const reviewImageReadFetch = async (reviewId: number) => {
    const response = await fetch(`http://localhost:8090/review/read/image?reviewId=${reviewId}`, {
        method: "GET",
        headers: {
            "Content-Type": "image/jpeg"
        }
    });

    // if (!response.ok) {
    //     throw new Error("Network response was not ok");
    // }

    const data = await response;

    const contentType = response.headers.get('content-type');
        
    if (contentType && contentType.includes("image")) {
        const blob = await response.blob();

        console.log(blob);
        

        return blob;
    }

    return data;
};

export const useReadReviewImage = (reviewId: number) => {
    return useQuery({
        queryKey: ["review_image_read"],
        queryFn: _ => reviewImageReadFetch(reviewId)
    });
};