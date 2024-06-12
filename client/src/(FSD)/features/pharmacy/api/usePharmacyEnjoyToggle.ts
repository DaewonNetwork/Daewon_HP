import { useMutation } from "@tanstack/react-query";
import { MutationType } from "../../types/mutation.type";
import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";

export const usePharmacyEnjoyToggle = ({ onSuccess, onError }: MutationType) => {
    return useMutation({
        mutationFn: (phId: number) => {
            return fetchData({ path: `/enjoy?phId=${phId}`, isAuthRequired: true })
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