import { useMutation } from "@tanstack/react-query";
import { MutationType } from "../../types/mutation.type";

const replyUpdateFetch = async (data: any) => {
    const response = await fetch("http://localhost:8090/api/reply", {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("access_token") || ""}`,
        },
        body: JSON.stringify(data),
    });

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    };

    const responseData = await response.json();

    return responseData;
}

export const useReplyUpdate = ({ onSuccess, onError }: MutationType) => {
    return useMutation({
        mutationFn: (data: any) => {
            return replyUpdateFetch(data);
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