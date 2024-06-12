"use client";

import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { useQuery } from "@tanstack/react-query";

export const useReadUser = () => {
    return useQuery({
        queryKey: ["user_read"],
        queryFn: () => fetchData({ path: "/api/user", isAuthRequired: true }),
    });
};