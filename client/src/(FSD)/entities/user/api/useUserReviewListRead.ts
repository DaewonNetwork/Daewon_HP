import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { useQuery } from "@tanstack/react-query";

export const useUserReviewListRead = () => {
    return useQuery({
        queryKey: ["user_review_list_read"],
        queryFn: _ => fetchData({ path: "/api/user/review", isAuthRequired: true })
    });
};