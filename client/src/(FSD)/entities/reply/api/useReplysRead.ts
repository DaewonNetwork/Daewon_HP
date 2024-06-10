import { useQuery } from "@tanstack/react-query";

const replysReadFetch = async (reviewId: number, isLoggedIn: boolean) => {
    let response = null;

    if (isLoggedIn) {
        response = await fetch(`http://localhost:8090/api/reply/list?reviewId=${reviewId}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${localStorage.getItem("access_token") || ""}`
            },
        });
    } else {
        response = await fetch(`http://localhost:8090/reply/list?reviewId=${reviewId}`, {
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

export const useReplysRead = (reviewId: number) => {
    return useQuery({
        queryKey: ["reply_list"],
        queryFn: _ => replysReadFetch(reviewId, !!localStorage.getItem("access_token")),
    });
};