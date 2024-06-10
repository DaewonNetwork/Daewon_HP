import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
import { useInfiniteQuery } from "@tanstack/react-query";
import { useMemo } from "react";

const phSearchNearFetch = async ({ pageParam = 1, queryKey, isLoggedIn }: { pageParam?: number, queryKey: any[], isLoggedIn: boolean }) => {
    const [, [lat, lng]] = queryKey;

    let response;
    if (isLoggedIn) {
        response = await fetch(`http://localhost:8090/api/pharmacy/near?lat=${lat}&lng=${lng}&pageIndex=${pageParam}&size=10`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${localStorage.getItem("access_token") || ""}`
            },
        });
    } else {
        response = await fetch(`http://localhost:8090/pharmacy/near?lat=${lat}&lng=${lng}&pageIndex=${pageParam}&size=10`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        });
    }

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    };

    const data = await response.json();

    return data;
};

export const usePharmacyNearSearch = (lat: number, lng: number) => {
    const {
        data,
        fetchNextPage,
        hasNextPage,
        isFetchingNextPage,
        isError,
        isLoading,
        refetch
    } = useInfiniteQuery({
        queryKey: ["search_near", [lat, lng]],
        queryFn: ({ pageParam, queryKey }) => phSearchNearFetch({ pageParam: pageParam, queryKey: queryKey, isLoggedIn: !!localStorage.getItem("access_token") }),
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

    return { pharmacyList, isLoading, isError, fetchNextPage, isFetchingNextPage, hasNextPage, refetch };
};
