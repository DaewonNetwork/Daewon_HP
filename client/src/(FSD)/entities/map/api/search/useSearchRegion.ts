import { useInfiniteQuery } from "@tanstack/react-query";
import { useEffect, useMemo } from "react";

const mapSearchRegionFetch = async ({ pageParam = 1, queryKey }: { pageParam?: number, queryKey: string[] }) => {
    const [, city] = queryKey;
    const response = await fetch(`http://localhost:8090/map/region?city=${city}&page=${pageParam}&size=10`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
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
        status,
        error
    } = useInfiniteQuery({
        queryKey: ["searchCity", city],
        queryFn: mapSearchRegionFetch,
        getNextPageParam: (lastPage, pages: any) => {
            console.log(pages[0]);
            
            const pageIndex = pages[0].page = pages[0]?.page + 1;
            console.log(pageIndex);
            

            return pageIndex;
        },
        initialPageParam: 1,
        refetchOnMount: false,
        refetchOnReconnect: false,
        refetchOnWindowFocus: false,
    });

    const pharmacyList = useMemo(() => {
        const pharmacyList = data?.pages || [];
        
        if(pharmacyList[0]?.phList) {
            return pharmacyList[0].phList as Array<any>;
        } else {
            return [];
        }
    }, [data]);

    return { pharmacyList, isLoading, isError, fetchNextPage, isFetchingNextPage };
};
