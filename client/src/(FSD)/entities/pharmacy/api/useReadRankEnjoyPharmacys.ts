import { useQuery } from "@tanstack/react-query";

const readRankEnjoyPharmacysFetch = async () => {
    const response = await fetch("http://localhost:8090/pharmacy/rank/enjoy", {
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

export const useReadRankEnjoyPharmacys = () => {
    return useQuery({
        queryKey: ["read_rank_enjoy_pharmacys"],
        queryFn: _ => readRankEnjoyPharmacysFetch(),
    });
};