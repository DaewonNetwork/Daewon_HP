"use client";

import React, { useEffect } from "react";
import { useParams } from "next/navigation";
import { useSearchRegion } from "@/(FSD)/entities/map/api/search/useSearchRegion";
import { PharmacyType } from "@/(FSD)/shareds/types/Pharmacy.type";

const Page = () => {
    const { city } = useParams<{ city: string }>();

    const { data, ref, isFetchingNextPage, status, error } = useSearchRegion(city);

    useEffect(() => {

    }, [city]);

    const pages = data?.pages || [];


    // console.log(data);
    
    const pharmacyList: PharmacyType[] = pages[0]?.phList || [];

    // if (!pharmacyList) return <></>;

    if (status === "pending") return <p>Loading...</p>;
    if (status === "error") return <p>Error: {(error as Error).message}</p>;

    return (
        <div ref={ref}>

            {
                pharmacyList.map((pharmacy, index) => (
                    <div key={index}>
                        <h1>{pharmacy.phName}</h1>
                        <p>{pharmacy.phAdd}</p>
                        <p>{pharmacy.phTel}</p>
                        <p>{pharmacy.enjoyIndex}</p>
                        <p>{pharmacy.starIndex}</p>
                    </div>
                ))
            }
        </div>
    );
};

export default Page;