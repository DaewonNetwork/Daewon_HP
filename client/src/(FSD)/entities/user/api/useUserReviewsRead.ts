import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { useQuery } from "@tanstack/react-query";

export const useUserReviewsRead = () => {
    return useQuery({
        queryKey: ["my_reviews_read"],
        queryFn: _ => fetchData({ path: "/api/user/review", isAuthRequired: true })
    });
};