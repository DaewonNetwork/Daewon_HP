import { useQuery } from "@tanstack/react-query"

const mapSearchCityFetch = async (word: string) => {
    const response = await fetch(`http://localhost:8090/map/region?city=${word}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        }
    });

    const responseData = await response.json();    

    return responseData;
}

export const useSearchCity = (word: string) => {
    return useQuery({
        queryKey: ["search_city"],
        queryFn: () => {
            return mapSearchCityFetch(word);
        }
    })
}
