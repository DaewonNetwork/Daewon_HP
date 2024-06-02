import { useMutation, useQuery } from "@tanstack/react-query";
import { MutationType } from "../../types/mutation.type";

const pharmacyEnjoyToggleFetch = async (phId: number) => {
    const response = await fetch(`http://localhost:8090/api/enjoy?phId=${phId}`, {
        method: "GET",
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
};

export const usePharmacyEnjoyToggle = ({ onSuccess, onError }: MutationType) => {
    return useMutation({
        mutationFn: (phId: number) => pharmacyEnjoyToggleFetch(phId),
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