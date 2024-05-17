"use client";
import { useQuery } from "@tanstack/react-query";

export const mapSearchFetch = async (keyword: string) => {
    const response = await fetch(`http://localhost:8090/api/map/search?keyword=${encodeURIComponent(keyword)}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    });

    if (!response.ok) {
        throw new Error("Network response was not ok");
    }

    const responseData = await response.json();

    return responseData;
}

export const useMapSearch = (keyword: string) => {
    return useQuery({
        queryKey: ["map_search"],
        queryFn: () => mapSearchFetch(keyword)
    });
};