import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { useQuery } from "@tanstack/react-query";

export const useReplyRead = (replyId: number) => {
    return useQuery({
        queryKey: ["reply_read"],
        queryFn: _ => fetchData({ path: `/reply/read?replyId=${replyId}` }),
    });
};