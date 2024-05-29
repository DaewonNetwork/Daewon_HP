import { useQuery } from "@tanstack/react-query";

const phSearchRegionKeywordFetch = async (city: string, keyword: string) => {
    const response = await fetch(`http://localhost:8090/map/region/search?city=${city}&keyword=${keyword}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    });

    const data = await response.json();
    
    return data;
};

export const useSearchRegionKeyword = (city: string, keyword: string) => {
    return useQuery({
        queryKey: ["map_region_keyword"],
        queryFn: () => phSearchRegionKeywordFetch(city, keyword)
    });
};