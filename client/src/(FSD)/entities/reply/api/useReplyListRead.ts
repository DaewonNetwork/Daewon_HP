import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { useQuery } from "@tanstack/react-query";

export const useReplyListRead = (reviewId: number) => {
    return useQuery({
        queryKey: ["reply_list_read"],
        queryFn: _ => fetchData({ path: `/reply/list?reviewId=${reviewId}` })
    });
};