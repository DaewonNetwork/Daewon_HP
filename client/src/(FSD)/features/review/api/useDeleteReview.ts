import { MutationType } from "@/(FSD)/features/types/mutation.type";
import { useMutation } from "@tanstack/react-query"

const reviewDeleteFetch = async (reviewId: number) => {
    const response = await fetch(`http://localhost:8090/api/review?reviewId=${reviewId}`, {
        method: "delete",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("access_token") || ""}`
        },
    });

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    };

    const data = await response.json();
    
    return data;
};

export const useDeleteReview = ({ onSuccess, onError }: MutationType) => {
    return useMutation({
        mutationFn: (reviewId: number) => {
            return reviewDeleteFetch(reviewId);
        },
        onSuccess: (data: any) => {
            onSuccess(data);
        },
        onError: _ => {
            if (onError) {
                onError();
            }
        }
    });
};