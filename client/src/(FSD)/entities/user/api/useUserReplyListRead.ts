import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { useQuery } from "@tanstack/react-query";

export const useUserReplyListRead = () => {
    return useQuery({
        queryKey: ["user_reply_list_read"],
        queryFn: _ => fetchData({ path: "/api/user/reply", isAuthRequired: true })
    });
};