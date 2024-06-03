import { useMutation } from "@tanstack/react-query";
import { MutationType } from "../../types/mutation.type";

const reviewCreateFetch = async (data: FormData) => {
    const response = await fetch("http://localhost:8090/api/review", {
        method: "POST",
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

export const useReviewCreate = ({ onSuccess, onError }: MutationType) => {
    return useMutation({
        mutationFn: (userData: FormData) => {
            return reviewCreateFetch(userData);
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