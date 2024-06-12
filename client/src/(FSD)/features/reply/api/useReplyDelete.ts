import { MutationType } from "@/(FSD)/features/types/mutation.type";
import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { useMutation } from "@tanstack/react-query";

export const useReplyDelete = ({ onSuccess, onError }: MutationType) => {
    return useMutation({
        mutationFn: (replyId: number) => {
            return fetchData({ path: `/reply?replyId=${replyId}`, method: "DELETE", isAuthRequired: true });
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