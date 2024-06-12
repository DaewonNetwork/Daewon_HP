import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { keepPreviousData, useQuery } from "@tanstack/react-query";

export const usePharmacyNearMap = (lat: number, lng: number) => {
    return useQuery({
        queryKey: ["map_near"],
        queryFn: () => fetchData({ path: `/map/near?lat=${lat}&lng=${lng}`, isNotAuthRequired: true }),
        placeholderData: keepPreviousData,
    });
};