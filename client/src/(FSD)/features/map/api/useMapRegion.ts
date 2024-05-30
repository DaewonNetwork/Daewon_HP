import { useQuery } from "@tanstack/react-query";
import { useEffect } from "react";

const phMapRegionFetch = async (city: string) => {
    const response = await fetch(`http://localhost:8090/map/region?city=${city}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    });

    const data = await response.json();
    
    return data;
};

export const useMapRegion = (city: string) => {
    return useQuery({
        queryKey: ["map_region"],
        queryFn: () => phMapRegionFetch(city),
    });
};