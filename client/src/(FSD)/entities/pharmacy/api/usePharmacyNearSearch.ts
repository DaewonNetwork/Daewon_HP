import { fetchData } from "@/(FSD)/shareds/fetch/fetchData";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
import { keepPreviousData, useInfiniteQuery } from "@tanstack/react-query";
import { useMemo } from "react";

export const usePharmacyNearSearch = (lat: number, lng: number) => {
    const {
        data,
        fetchNextPage,
        hasNextPage,
        isFetchingNextPage,
        isError,
        isPending,
        refetch
    } = useInfiniteQuery({
        queryKey: ["search_near", [lat, lng]],
        queryFn: ({ pageParam }) => fetchData({ path: `/pharmacy/near?lat=${lat}&lng=${lng}&pageIndex=${pageParam}&size=10` }),
        getNextPageParam: (lastPage) => {
            console.log(lastPage);
            
            if (lastPage.next) {
                console.log(lastPage.pageIndex + 1);
                
                return lastPage.pageIndex + 1;
            }
            return undefined;
        },
        initialPageParam: 1,
        refetchOnMount: false,
        refetchOnReconnect: false,
        refetchOnWindowFocus: false,
    });

    const pharmacyList: PharmacyType[] = useMemo(() => {
        const pharmacyList = data?.pages?.flatMap(page => page.phList) || [];
        return pharmacyList;
    }, [data]);

    return { pharmacyList, isPending, isError, fetchNextPage, isFetchingNextPage, hasNextPage, refetch };
};