import { keepPreviousData, useQuery } from "@tanstack/react-query";

const phMapKeywordFetch = async (keyword: string) => {
    const response = await fetch(`http://localhost:8090/map/search?keyword=${keyword}`, {
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

export const useMapKeyword = (keyword: string) => {
    return useQuery({
        queryKey: ["map_keyword"],
        queryFn: () => phMapKeywordFetch(keyword),
        placeholderData: keepPreviousData,
        
    });
};