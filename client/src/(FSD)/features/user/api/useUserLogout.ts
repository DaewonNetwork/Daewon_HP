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

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    };

    const data = await response.json();
    
    return data;
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