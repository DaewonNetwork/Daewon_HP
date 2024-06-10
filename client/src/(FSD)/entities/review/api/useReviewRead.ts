import { useQuery } from "@tanstack/react-query";

const reviewReadFetch = async (reviewId: number, isLoggedIn: boolean) => {
    let response = null;
    
    if(isLoggedIn) {
        response = await fetch(`http://localhost:8090/api/review/read?reviewId=${reviewId}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${localStorage.getItem("access_token") || ""}`
            },
        });
    } else {
        response = await fetch(`http://localhost:8090/review/read?reviewId=${reviewId}`, {
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

export const useReviewRead = (reviewId: number) => {
    return useQuery({
        queryKey: ["review_read"],
        queryFn: _ => reviewReadFetch(reviewId, !!localStorage.getItem("access_token")),
    });
};