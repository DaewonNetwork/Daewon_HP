import { useQuery } from "@tanstack/react-query";

const myReplyReadFetch = async () => {
    const response = await fetch("http://localhost:8090/api/user/reply", {
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

export const useUserReplysRead = () => {
    return useQuery({
        queryKey: ["my_reply_read"],
        queryFn: _ => myReplyReadFetch(),
    });
};