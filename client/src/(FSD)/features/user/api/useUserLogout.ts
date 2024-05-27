"use client";

import { useMutation } from "@tanstack/react-query";
import { MutationType } from "../../types/mutation.type";

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

    const responseData = await response.json();

    return responseData;
}

export const useUserLogout = ({ onSuccess, onError }: MutationType) => {
    return useMutation({
        mutationFn: () => {
            return userLogoutFetch();
        },
        onSuccess: (data: any) => {
            console.log(data);

            onSuccess(data);
        },
        onError: _ => {
            if (onError) {
                onError();
            }
        }
    });
};