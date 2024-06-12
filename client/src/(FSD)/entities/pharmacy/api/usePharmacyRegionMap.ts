import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { keepPreviousData, useQuery } from "@tanstack/react-query";

export const usePharmacyRegionMap = (city: string) => {
    return useQuery({
        queryKey: ["map_region"],
        queryFn: () => fetchData({ path: `/map/region?city=${city}`, isNotAuthRequired: true }),
        placeholderData: keepPreviousData,
    });
};