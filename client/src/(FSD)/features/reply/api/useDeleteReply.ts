import { MutationType } from "@/(FSD)/features/types/mutation.type";
import { useMutation } from "@tanstack/react-query"

const ReplyDeleteFetch = async (replyId: number) => {
    const response = await fetch(`http://localhost:8090/api/reply?replyId=${replyId}`, {
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

export const useDeleteReply = ({ onSuccess, onError }: MutationType) => {
    return useMutation({
        mutationFn: (replyId: number) => {
            return ReplyDeleteFetch(replyId);
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