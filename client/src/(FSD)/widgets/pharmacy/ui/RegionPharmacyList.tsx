"use client";

import { useParams } from "next/navigation";
import React, { useEffect } from "react";
import { useInView } from "react-intersection-observer";
import { useSearchRegion } from "@/(FSD)/features/pharmacy/api/useSearchRegion";
import PharmacyShared from "@/(FSD)/shareds/ui/PharmacyShared";

const RegionPharmacyList = () => {
    const { city } = useParams<{ city: string }>();

    const { pharmacyList, fetchNextPage } = useSearchRegion(city);

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
                        <PharmacyShared pharmacy={pharmacy} />
                    </React.Fragment>
                ))
            }
            <div ref={ref} />
        </>
    );
};

export default RegionPharmacyList;