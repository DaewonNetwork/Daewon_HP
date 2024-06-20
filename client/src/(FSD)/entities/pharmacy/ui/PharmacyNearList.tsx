"use client";

import React, { useEffect } from "react";
import { useInView } from "react-intersection-observer";
import { usePharmacyNearSearch } from "@/(FSD)/entities/pharmacy/api/usePharmacyNearSearch";
import PharmacyItem from "@/(FSD)/widgets/pharmacy/ui/PharmacyItem";
import PharmacySkeletonShared from "@/(FSD)/shareds/ui/PharmacySkeletonShared";

const PharmacyNearList = ({ lat, lng, isGeoPending } : { lat: number, lng: number, isGeoPending: boolean }) => {
    const { pharmacyList, fetchNextPage, refetch, isFetchingNextPage, isPending, isError } = usePharmacyNearSearch(lat, lng);

    const { ref, inView } = useInView();

    useEffect(() => {
        refetch();
    }, [pharmacyList]);

    useEffect(() => {
        if (inView) {
            fetchNextPage();
        }
    }, [inView]);

    if(isError) return <></>;
    if(!pharmacyList[0]) return <></>;

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

export default PharmacyNearList;