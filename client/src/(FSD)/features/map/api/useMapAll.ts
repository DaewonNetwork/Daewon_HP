import { useQuery } from "@tanstack/react-query";

const phMapAllFetch = async () => {
    const response = await fetch("http://localhost:8090/map/all", {
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

export const useMapAll = () => {
    return useQuery({
        queryKey: ["map_all"],
        queryFn: phMapAllFetch,
    });
};