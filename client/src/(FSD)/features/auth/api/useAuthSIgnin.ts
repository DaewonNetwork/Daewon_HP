import { useMutation } from "@tanstack/react-query";
import { MutationType } from "../../types/mutation.type";
import { UserType } from "@/(FSD)/shareds/types/User.type";

const authSigninFetch = async (data: UserType) => {
    const response = await fetch("http://localhost:8090/auth/signin", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("access_token") || ""}`
        },
        body: JSON.stringify(data),
    });

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    };

    const responseData = await response.json();

    return responseData;
}

export const useAuthSignin = ({ onSuccess, onError }: MutationType) => {
    return useMutation({
        mutationFn: (userData: UserType) => {
            return authSigninFetch(userData);
        },
        onSuccess: (data: UserType) => {
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