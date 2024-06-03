import useUserStore from "@/(FSD)/shareds/stores/useUserStore";
import { useQuery } from "@tanstack/react-query";

const pharmacyReadFetch = async (phId: number, isLoggedIn: boolean) => {
    let response = null;
    
    if(isLoggedIn) {
        response = await fetch(`http://localhost:8090/api/pharmacy/read?phId=${phId}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${localStorage.getItem("access_token") || ""}`
            },
        });
    } else {
        response = await fetch(`http://localhost:8090/pharmacy/read?phId=${phId}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        });
    }

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    };

    const data = await response.json();
    
    return data;
};

export const useReadPharmacy = (phId: number) => {
    const { isLoggedIn } = useUserStore();
    return useQuery({
        queryKey: ["pharmacy_read"],
        queryFn: _ => pharmacyReadFetch(phId, isLoggedIn),
    });
};