import { useInfiniteQuery } from "@tanstack/react-query";
import { useEffect } from "react";
import { useInView } from 'react-intersection-observer';

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
        status,
        error
    } = useInfiniteQuery({
        queryKey: ["searchCity", city],
        queryFn: mapSearchRegionFetch,
        getNextPageParam: (lastPage, pages: any) => {
            console.log(pages[0]?.page + 1);
            
            return pages[0]?.page + 1;
            // console.log(lastPage, pages);
            
            // if (lastPage.length === 10) {
            //     return pages.page + 1;
            // }
            // return undefined;
        },
        initialPageParam: 1,
    });

    const { ref, inView } = useInView();

    useEffect(() => {
        // console.log(inView);
        
        if (inView) {
            fetchNextPage();
        }
    }, [inView, hasNextPage, fetchNextPage]);

    return { data, ref, isFetchingNextPage, status, error };
};
