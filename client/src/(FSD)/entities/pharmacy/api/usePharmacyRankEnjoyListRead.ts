import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { useQuery } from "@tanstack/react-query";

export const usePharmacyRankEnjoyListRead = () => {
    return useQuery({
        queryKey: ["read_rank_enjoy_pharmacys"],
        queryFn: _ => fetchData({ path: "/pharmacy/rank/enjoy" }),
    });
};