import { useInfiniteQuery } from "@tanstack/react-query";
import { useInView } from 'react-intersection-observer';

const mapSearchCityFetch = async ({ pageParam = 1, queryKey }: { pageParam?: number, queryKey: string[] }) => {
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

export const useSearchCity = (city: string) => {
    const {
        data,
        fetchNextPage,
        hasNextPage,
        isFetchingNextPage,
        status,
        error
    } = useInfiniteQuery({
        queryKey: ["searchCity", city],
        queryFn: mapSearchCityFetch,
        getNextPageParam: (lastPage, pages) => {
            if (lastPage.length === 10) {
                return pages.length + 1;
            }
            return undefined;
        },
        initialPageParam: 1, // Add this line
    });

    const { ref } = useInView();

    return { data, ref, isFetchingNextPage, status, error };
};
