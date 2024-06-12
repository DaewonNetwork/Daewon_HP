import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { useQuery } from "@tanstack/react-query";

export const useReviewRead = (reviewId: number) => {
    return useQuery({
        queryKey: ["review_read"],
        queryFn: _ => fetchData({ path: `/review/read?reviewId=${reviewId}` }),
    });
};