"use client";

import React, { useEffect } from "react";
import { useInView } from "react-intersection-observer";
import { usePharmacyRegionSearch } from "@/(FSD)/entities/pharmacy/api/usePharmacyRegionSearch";
import PharmacyShared from "@/(FSD)/shareds/ui/PharmacyShared";
import PharmacySkeletonShared from "@/(FSD)/shareds/ui/PharmacySkeletonShared";
import { useSearchParams } from "next/navigation";

const PharmacyRegionList = () => {
    const searchParams = useSearchParams();
    const city = searchParams.get("city")!;

    const { pharmacyList, fetchNextPage, refetch, isFetchingNextPage } = usePharmacyRegionSearch(city);
    const { ref, inView } = useInView();
    
    useEffect(() => {}, [city]);

    useEffect(() => {
        if (inView) {
            fetchNextPage();
        }
    }, [inView]);

    if(!pharmacyList) return <></>;

    return (
        <>
            {
                pharmacyList.map(pharmacy => (
                    <React.Fragment key={pharmacy.phId}>
                        <PharmacyShared pharmacy={pharmacy} parentRefetch={refetch} />
                    </React.Fragment>
                ))
            }
            {
                isFetchingNextPage ? <>
                    <PharmacySkeletonShared />
                    {
                        Array.from({ length: 9 }).map((_, index) => (
                            <React.Fragment key={index}>
                                <PharmacySkeletonShared />
                            </React.Fragment>
                        ))
                    }
                </> : <div ref={ref} />
            }
        </>
    );
};

export default PharmacyRegionList;