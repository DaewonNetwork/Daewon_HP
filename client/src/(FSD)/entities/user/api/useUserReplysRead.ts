import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { useQuery } from "@tanstack/react-query";

export const useUserReplysRead = () => {
    return useQuery({
        queryKey: ["my_reply_read"],
        queryFn: _ => fetchData({ path: "/api/user/reply", isAuthRequired: true })
    });
};