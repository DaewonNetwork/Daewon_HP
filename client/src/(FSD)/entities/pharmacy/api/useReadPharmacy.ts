import { useQuery } from "@tanstack/react-query";

const pharmacyReadFetch = async (phId: number) => {
    const response = await fetch(`http://localhost:8090/pharmacy/read?phId=${phId}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    });

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    };

    const data = await response.json();
    
    return data;
};

export const useReadPharmacy = (phId: number) => {
    return useQuery({
        queryKey: ["pharmacy_read"],
        queryFn: _ => pharmacyReadFetch(phId),
    });
};