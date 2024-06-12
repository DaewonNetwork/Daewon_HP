import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { useQuery } from "@tanstack/react-query";

export const usePharmacyRead = (phId: number) => {
    return useQuery({
        queryKey: ["pharmacy_read"],
        queryFn: _ => fetchData({ path: `/pharmacy/read?phId=${phId}` }),
    });
};