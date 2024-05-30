import { useQuery } from "@tanstack/react-query";

const phMapKeywordFetch = async (keyword: string) => {
    const response = await fetch(`http://localhost:8090/map/search?keyword=${keyword}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    });

    const data = await response.json();
    
    return data;
};

export const useMapKeyword = (keyword: string) => {
    return useQuery({
        queryKey: ["map_keyword"],
        queryFn: () => phMapKeywordFetch(keyword),
    });
};