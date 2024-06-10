import { useQuery } from "@tanstack/react-query";

const readRankEnjoyPharmacysFetch = async (isLoggedIn: boolean) => {
    let response;

    if(isLoggedIn) {
        response = await fetch("http://localhost:8090/api/pharmacy/rank/enjoy", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${localStorage.getItem("access_token") || ""}`
            },
        });
    } else {
        response = await fetch("http://localhost:8090/pharmacy/rank/enjoy", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        });
    }

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    };

    const data = await response.json();
    
    return data;
};

export const usePharmacyRankEnjoyListRead = () => {
    return useQuery({
        queryKey: ["read_rank_enjoy_pharmacys"],
        queryFn: _ => readRankEnjoyPharmacysFetch(!!localStorage.getItem("access_token")),
    });
};