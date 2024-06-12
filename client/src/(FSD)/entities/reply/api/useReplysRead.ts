import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { useQuery } from "@tanstack/react-query";

export const useReplysRead = (reviewId: number) => {
    return useQuery({
        queryKey: ["replys_read"],
        queryFn: _ => fetchData({ path: `/reply/list?reviewId=${reviewId}` })
    });
};