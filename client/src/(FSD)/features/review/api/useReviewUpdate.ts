import { useMutation } from "@tanstack/react-query";
import { MutationType } from "../../types/mutation.type";

const reviewUpdateFetch = async (data: FormData, reviewId: number) => {
    const response = await fetch(`http://localhost:8090/api/review?reviewId=${reviewId}`, {
        method: "PUT",
        headers: {
            Authorization: `Bearer ${localStorage.getItem("access_token") || ""}`,
        },
        body: data,
    });

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    };

    const responseData = await response.json();

    return responseData;
}

export const useReviewUpdate = ({ onSuccess, onError }: MutationType) => {
    return useMutation({
        mutationFn: ({ data, reviewId } : { data: FormData; reviewId: number; }) => {
            return reviewUpdateFetch(data, reviewId);
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