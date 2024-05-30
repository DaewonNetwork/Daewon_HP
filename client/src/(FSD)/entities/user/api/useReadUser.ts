import { useQuery } from "@tanstack/react-query";

const readUserFetch = async () => {
    const response = await fetch(`http://localhost:8090/api/user`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("access_token") || ""}`
        },
    });

    const data = await response.json();

    return data;
};

export const useReadUser = () => {
    return useQuery({
        queryKey: ["user_read"],
        queryFn: () => readUserFetch(),
    });
};