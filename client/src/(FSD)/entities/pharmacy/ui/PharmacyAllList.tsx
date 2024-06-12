"use client";

import React, { useEffect } from "react";
import { useInView } from "react-intersection-observer";
import { usePharmacyAllSearch } from "@/(FSD)/entities/pharmacy/api/usePharmacyAllSearch";
import PharmacyShared from "@/(FSD)/shareds/ui/PharmacyShared";
import PharmacySkeletonShared from "@/(FSD)/shareds/ui/PharmacySkeletonShared";

const PharmacyAllList = () => {
    const { pharmacyList, fetchNextPage, refetch, isFetchingNextPage } = usePharmacyAllSearch();

    const { ref, inView } = useInView();

    useEffect(() => {
        if (inView) {
            fetchNextPage();
        }
    }, [inView]);

    return (
        <>
            {
                pharmacyList.map((pharmacy, index) => (
                    <React.Fragment key={index}>
                        <PharmacyShared parentRefetch={refetch} pharmacy={pharmacy} />
                    </React.Fragment>
                ))
            }
            {isFetchingNextPage ? <>
                <PharmacySkeletonShared />
                {
                    Array.from({ length: 9 }).map((_, index) => (
                        <React.Fragment key={index}>
                            <PharmacySkeletonShared />
                        </React.Fragment>
                    ))
                }
            </> : <div ref={ref} />}
        </>
    );
};

export default PharmacyAllList;