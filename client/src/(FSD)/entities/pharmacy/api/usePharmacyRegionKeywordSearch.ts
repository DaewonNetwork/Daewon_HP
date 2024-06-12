import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
import { keepPreviousData, useInfiniteQuery } from "@tanstack/react-query";
import { useMemo } from "react";

export const usePharmacyRegionKeywordSearch = (city: string, keyword: string) => {
    const {
        data,
        fetchNextPage,
        hasNextPage,
        isFetchingNextPage,
        isError,
        isLoading,
        refetch
    } = useInfiniteQuery({
        queryKey: ["search_region_keyword", [city, keyword]],
        queryFn: ({ pageParam }) => fetchData({ path: `/pharmacy/region/search?city=${city}&keyword=${keyword}&pageIndex=${pageParam}&size=10` }),
        getNextPageParam: (lastPage) => {
            if (lastPage.next) {
                return lastPage.pageIndex + 1;
            }
            return undefined;
        },
        initialPageParam: 1,
        refetchOnMount: false,
        refetchOnReconnect: false,
        refetchOnWindowFocus: false,
        placeholderData: keepPreviousData,
        
    });

    const pharmacyList: PharmacyType[] = useMemo(() => {
        const pharmacyList = data?.pages?.flatMap(page => page.phList) || [];
        return pharmacyList;
    }, [data]);

    return { pharmacyList, isLoading, isError, fetchNextPage, isFetchingNextPage, hasNextPage, refetch };
};