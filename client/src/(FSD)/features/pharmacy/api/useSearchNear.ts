import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
import { useInfiniteQuery } from "@tanstack/react-query";
import { useMemo } from "react";

const phSearchNearFetch = async ({ pageParam = 1, queryKey }: { pageParam?: number, queryKey: any[] }) => {
    const [, [lat, lng]] = queryKey;
    const response = await fetch(`http://localhost:8090/pharmacy/near?lat=${lat}&lng=${lng}&pageIndex=${pageParam}&size=10`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    });

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    };

    const data = await response.json();
    
    return data;
};

export const useSearchNear = (lat: number, lng: number) => {
    const {
        data,
        fetchNextPage,
        hasNextPage,
        isFetchingNextPage,
        isError,
        isLoading,
    } = useInfiniteQuery({
        queryKey: ["search_near", [lat, lng]],
        queryFn: phSearchNearFetch,
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
