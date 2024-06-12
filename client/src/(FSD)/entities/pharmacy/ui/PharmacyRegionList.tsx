"use client";

import { useParams } from "next/navigation";
import React, { useEffect } from "react";
import { useInView } from "react-intersection-observer";
import { usePharmacyRegionSearch } from "@/(FSD)/entities/pharmacy/api/usePharmacyRegionSearch";
import PharmacyShared from "@/(FSD)/shareds/ui/PharmacyShared";

const PharmacyRegionList = () => {
    const { city } = useParams<{ city: string }>();

    const { pharmacyList, fetchNextPage, refetch } = usePharmacyRegionSearch(city);

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
                        <PharmacyShared pharmacy={pharmacy} parentRefetch={refetch} />
                    </React.Fragment>
                ))
            }
            <div ref={ref} />
        </>
    );
};

export default PharmacyRegionList;