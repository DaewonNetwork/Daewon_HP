import { useMutation } from "@tanstack/react-query";
import { MutationType } from "../../types/mutation.type";
import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";

export const useReplyCreate = ({ onSuccess, onError }: MutationType) => {
    return useMutation({
        mutationFn: (data: any) => {
            return fetchData({ path: "/reply", method: "POST", body: data, isAuthRequired: true });
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