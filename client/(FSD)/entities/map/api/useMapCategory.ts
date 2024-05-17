import { useQuery } from "@tanstack/react-query";

const mapCategoryFetch = async (keyword: string) => {
    const response = await fetch(`http://localhost:8090/map/category?keyword=${encodeURIComponent(keyword)}`, {
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

export const useMapCategory = (keyword: string) => {
    return useQuery({
        queryKey: ["map_Category"],
        queryFn: () => mapCategoryFetch(keyword)
    })
};