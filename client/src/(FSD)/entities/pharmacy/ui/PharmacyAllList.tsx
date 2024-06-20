"use client";

import React, { useEffect } from "react";
import { useInView } from "react-intersection-observer";
import { usePharmacyAllSearch } from "@/(FSD)/entities/pharmacy/api/usePharmacyAllSearch";
import PharmacyItem from "@/(FSD)/widgets/pharmacy/ui/PharmacyItem";
import PharmacySkeletonShared from "@/(FSD)/shareds/ui/PharmacySkeletonShared";

const PharmacyAllList = () => {
    const { pharmacyList, fetchNextPage, refetch, isFetchingNextPage, isError } = usePharmacyAllSearch();

    const { ref, inView } = useInView();

    useEffect(() => {
        refetch();
    }, [pharmacyList]);

    useEffect(() => {
        if (inView) {
            fetchNextPage();
        }
    }, [inView]);

    return (
        <>
            {
                pharmacyList.map(pharmacy => (
                    <React.Fragment key={pharmacy.phId}>
                        <PharmacyItem parentRefetch={refetch} pharmacy={pharmacy} />
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

export default PharmacyAllList;