"use client";

import { notFound, useSearchParams } from "next/navigation";
import React, { useEffect } from "react";
import { useInView } from "react-intersection-observer";
import { usePharmacyRegionKeywordSearch } from "@/(FSD)/entities/pharmacy/api/usePharmacyRegionKeywordSearch";
import PharmacyShared from "@/(FSD)/shareds/ui/PharmacyShared";
import PharmacySkeletonShared from "@/(FSD)/shareds/ui/PharmacySkeletonShared";

const PharmacyRegionKeywordList = () => {
    const searchParams = useSearchParams();
    const city = searchParams.get("city")!;
    const keyword = searchParams.get("keyword")!;

    const { pharmacyList, fetchNextPage, refetch, isFetchingNextPage, isError } = usePharmacyRegionKeywordSearch(city, keyword);

    const { ref, inView } = useInView();

    useEffect(() => {
        if (inView) {
            fetchNextPage();
        }
    }, [inView]);

    if(isError && (pharmacyList && (!pharmacyList[0]))) return notFound();

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

export default PharmacyRegionKeywordList;