import { useQuery } from "@tanstack/react-query";

const replyReadFetch = async (reviewId: number) => {
    const response = await fetch(`http://localhost:8090/reply?reviewId=${reviewId}`, {
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

export const useReadReply = (reviewId: number) => {
    return useQuery({
        queryKey: ["reply_read"],
        queryFn: _ => replyReadFetch(reviewId),
    });
};