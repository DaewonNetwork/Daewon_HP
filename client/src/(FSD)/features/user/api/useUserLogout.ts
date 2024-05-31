import { useMutation } from "@tanstack/react-query";
import { MutationNullType, MutationType } from "../../types/mutation.type";

const userLogoutFetch = async () => {
    const response = await fetch("http://localhost:8090/api/user/logout", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("access_token") || ""}`
        },
    });

    return response;
}

export const useUserLogout = ({ onSuccess, onError }: MutationNullType) => {
    return useMutation({
        mutationFn: () => {
            return userLogoutFetch();
        },
        onSuccess: () => {
            if(onSuccess) {
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