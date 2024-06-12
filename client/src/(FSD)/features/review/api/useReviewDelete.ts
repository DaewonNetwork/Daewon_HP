import { MutationType } from "@/(FSD)/features/types/mutation.type";
import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { useMutation } from "@tanstack/react-query"

export const useReviewDelete = ({ onSuccess, onError }: MutationType) => {
    return useMutation({
        mutationFn: (reviewId: number) => {
            return fetchData({ path: `/review?reviewId=${reviewId}`, method: "DELETE", isAuthRequired: true });
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