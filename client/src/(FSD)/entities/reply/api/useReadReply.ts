import { useQuery } from "@tanstack/react-query";

const replyReadFetch = async (replyId: number, isLoggedIn: boolean) => {
    let response = null;

    if (isLoggedIn) {
        response = await fetch(`http://localhost:8090/api/reply/read?replyId=${replyId}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${localStorage.getItem("access_token") || ""}`
            },
        });
    } else {
        response = await fetch(`http://localhost:8090/reply/read?replyId=${replyId}`, {
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

export const useReadReply = (replyId: number) => {
    return useQuery({
        queryKey: ["reply_read"],
        queryFn: _ => replyReadFetch(replyId, !!localStorage.getItem("access_token")),
    });
};