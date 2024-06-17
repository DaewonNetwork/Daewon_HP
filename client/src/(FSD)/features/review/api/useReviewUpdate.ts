import { useMutation } from "@tanstack/react-query";
import { MutationType } from "../../types/mutation.type";
import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";

export const useReviewUpdate = ({ onSuccess, onError }: MutationType) => {
    return useMutation({
        mutationFn: ({ reviewId, data }: { reviewId: number; data: FormData; }) => {
            return fetchData({ path: `/review?reviewId=${reviewId}`, method: "PUT", body: JSON.stringify(data), isAuthRequired: true, contentType: "multipart/form-data" });
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