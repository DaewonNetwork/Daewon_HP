import { keepPreviousData, useQuery } from "@tanstack/react-query";

const phMapRegionKeywordFetch = async (city: string, keyword: string) => {
    const response = await fetch(`http://localhost:8090/map/region/search?city=${city}&keyword=${keyword}`, {
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

export const useMapRegionKeyword = (city: string, keyword: string) => {
    return useQuery({
        queryKey: ["map_region_keyword"],
        queryFn: () => phMapRegionKeywordFetch(city, keyword),
        placeholderData: keepPreviousData,
        
    });
};