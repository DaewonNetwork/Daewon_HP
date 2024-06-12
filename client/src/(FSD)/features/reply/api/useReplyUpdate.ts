import { useMutation } from "@tanstack/react-query";
import { MutationType } from "../../types/mutation.type";
import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";

export const useReplyUpdate = ({ onSuccess, onError }: MutationType) => {
    return useMutation({
        mutationFn: ({ replyId, data } : { replyId: number; data: any; }) => {
            return fetchData({ path: `/reply?replyId=${replyId}`, method: "PUT", body: data, isAuthRequired: true });
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