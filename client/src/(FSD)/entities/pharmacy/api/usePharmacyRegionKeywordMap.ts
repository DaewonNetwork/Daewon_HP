import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { keepPreviousData, useQuery } from "@tanstack/react-query";

export const usePharmacyRegionKeywordMap = (city: string, keyword: string) => {
    return useQuery({
        queryKey: ["map_region_keyword"],
        queryFn: () => fetchData({ path: `/map/region/search?city=${city}&keyword=${keyword}`, isNotAuthRequired: true }),
        placeholderData: keepPreviousData,
    });
};