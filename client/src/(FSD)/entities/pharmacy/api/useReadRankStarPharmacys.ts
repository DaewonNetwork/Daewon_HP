import { useQuery } from "@tanstack/react-query";

const readRankStarPharmacysFetch = async () => {
    const response = await fetch("http://localhost:8090/pharmacy/rank/star", {
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

export const useReadRankStarPharmacys = () => {
    return useQuery({
        queryKey: ["read_rank_star_pharmacys"],
        queryFn: _ => readRankStarPharmacysFetch(),
    });
};