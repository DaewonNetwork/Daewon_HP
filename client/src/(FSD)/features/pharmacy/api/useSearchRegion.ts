import { PharmacyType } from "@/(FSD)/shareds/types/Pharmacy.type";
import { useInfiniteQuery } from "@tanstack/react-query";
import { useMemo } from "react";

const phSearchRegionFetch = async ({ pageParam = 1, queryKey }: { pageParam?: number, queryKey: string[] }) => {
    const [, city] = queryKey;
    const response = await fetch(`http://localhost:8090/pharmacy/region?city=${city}&pageIndex=${pageParam}&size=10`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    });

    const data = await response.json();
    
    return data;
};

export const useSearchRegion = (city: string) => {
    const {
        data,
        fetchNextPage,
        hasNextPage,
        isFetchingNextPage,
        isError,
        isLoading,
    } = useInfiniteQuery({
        queryKey: ["search_region", city],
        queryFn: phSearchRegionFetch,
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
    });

    const pharmacyList: PharmacyType[] = useMemo(() => {
        const pharmacyList = data?.pages?.flatMap(page => page.phList) || [];
        return pharmacyList;
    }, [data]);

    return { pharmacyList, isLoading, isError, fetchNextPage, isFetchingNextPage, hasNextPage };
};
