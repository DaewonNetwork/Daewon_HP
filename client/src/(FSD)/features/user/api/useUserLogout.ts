import { useMutation } from "@tanstack/react-query";
import { MutationNullType } from "../../types/mutation.type";
import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";

export const useUserLogout = ({ onSuccess, onError }: MutationNullType) => {
    return useMutation({
        mutationFn: () => {
            return fetchData({ path: "/user/logout", method: "POST", isAuthRequired: true })
        },
        onSuccess: () => {
            if (onSuccess) {
                onSuccess();
            }
        },
        onError: _ => {
            if (onError) {
                onError();
            }
        }
    });
};