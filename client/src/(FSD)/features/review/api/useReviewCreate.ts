import { useMutation } from "@tanstack/react-query";
import { MutationType } from "../../types/mutation.type";
import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";

export const useReviewCreate = ({ onSuccess, onError }: MutationType) => {
    return useMutation({
        mutationFn: (data: FormData) => {
            return fetchData({ path: "/review", method: "POST", body: data, isAuthRequired: true });
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