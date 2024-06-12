import { useQuery } from "@tanstack/react-query";

const phMapNearFetch = async (lat: number, lng: number) => {
    const response = await fetch(`http://localhost:8090/pharmacy/near?lat=${lat}&lng=${lng}`, {
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

export const usePharmacyNearMap = (lat: number, lng: number) => {
    return useQuery({
        queryKey: ["map_near"],
        queryFn: () => phMapNearFetch(lat, lng),
    });
};