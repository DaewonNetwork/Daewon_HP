import { useQuery } from "@tanstack/react-query";

const myReviewsReadFetch = async () => {
    const response = await fetch("http://localhost:8090/api/user/review", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("access_token") || ""}`
        },
    });

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    };

    const data = await response.json();
    
    return data;
};

export const useUserReviewsRead = () => {
    return useQuery({
        queryKey: ["my_reviews_read"],
        queryFn: _ => myReviewsReadFetch(),
    });
};