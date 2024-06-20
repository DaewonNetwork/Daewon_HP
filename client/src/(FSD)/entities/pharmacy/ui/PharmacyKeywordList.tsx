"use client";

import { notFound, useSearchParams } from "next/navigation";
import React, { useEffect } from "react";
import { useInView } from "react-intersection-observer";
import PharmacyItem from "@/(FSD)/widgets/pharmacy/ui/PharmacyItem";
import { usePharmacyKeywordSearch } from "@/(FSD)/entities/pharmacy/api/usePharmacyKeywordSearch";
import PharmacySkeletonShared from "@/(FSD)/shareds/ui/PharmacySkeletonShared";

const PharmacyKeywordList = () => {
    const searchParams = useSearchParams();
    const keyword = searchParams.get("keyword")!;

    const { pharmacyList, fetchNextPage, refetch, isFetchingNextPage, isError } = usePharmacyKeywordSearch(keyword);

    const { ref, inView } = useInView();

    useEffect(() => {
        refetch();
    }, [pharmacyList]);
    
    useEffect(() => {
        if (inView) {
            fetchNextPage();
        }
    }, [inView]);

    if (isError && (pharmacyList && (!pharmacyList[0]))) return notFound();

    return (
        <>
            {
                pharmacyList.map(pharmacy => (
                    <React.Fragment key={pharmacy.phId}>
                        <PharmacyItem pharmacy={pharmacy} parentRefetch={refetch} />
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

export default PharmacyKeywordList;