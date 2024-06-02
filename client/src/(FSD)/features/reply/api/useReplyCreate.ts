import { useMutation } from "@tanstack/react-query";
import { MutationType } from "../../types/mutation.type";

const replyCreateFetch = async (data: any) => {
    const response = await fetch("http://localhost:8090/api/reply", {
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

export const useReplyCreate = ({ onSuccess, onError }: MutationType) => {
    return useMutation({
        mutationFn: (userData: any) => {
            return replyCreateFetch(userData);
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