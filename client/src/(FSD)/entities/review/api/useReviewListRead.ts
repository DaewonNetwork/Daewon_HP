import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { useQuery } from "@tanstack/react-query";

export const useReviewListRead = (phId: number) => {
    return useQuery({
        queryKey: ["reviews_read"],
        queryFn: _ => fetchData({ path: `/review/list?phId=${phId}` })
    });
};