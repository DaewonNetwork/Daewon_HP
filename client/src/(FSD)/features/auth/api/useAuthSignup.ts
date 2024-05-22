import { useMutation } from "@tanstack/react-query";
import { MutationType } from "../types/mutation.type";

const userAuthSignup = async (data: any) => {
    const response = await fetch("http://localhost:8090/api/auth/signup", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        credentials: "include",
        body: JSON.stringify(data),
    });

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    };

    const responseData = await response.json();

    return responseData;
}

export const useAuthSignup = ({ onSuccess, onError }: MutationType) => {
    return useMutation({
        mutationFn: (userData: any) => {
            return userAuthSignup(userData);
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