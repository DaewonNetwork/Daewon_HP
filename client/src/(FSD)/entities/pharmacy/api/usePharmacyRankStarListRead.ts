import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { useQuery } from "@tanstack/react-query";

export const usePharmacyRankStarListRead = () => {
    return useQuery({
        queryKey: ["read_rank_star_pharmacys"],
        queryFn: _ => fetchData({ path: "/pharmacy/rank/star" })
    });
};