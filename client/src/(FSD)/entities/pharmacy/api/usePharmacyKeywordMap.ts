import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { keepPreviousData, useQuery } from "@tanstack/react-query";

export const usePharmacyKeywordMap = (keyword: string) => {
    return useQuery({
        queryKey: ["map_keyword"],
        queryFn: () => fetchData({ path: `/map/search?keyword=${keyword}`, isNotAuthRequired: true }),
        placeholderData: keepPreviousData,
    });
};