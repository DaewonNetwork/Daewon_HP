"use client";

import { useParams } from "next/navigation";
import React, { useEffect } from "react";
import { useInView } from "react-intersection-observer";
import { useSearchRegionKeyword } from "@/(FSD)/features/pharmacy/api/useSearchRegionKeyword";
import PharmacyShared from "@/(FSD)/shareds/ui/PharmacyShared";

const RegionKeywordPharmacyList = () => {
    const { city, keyword } = useParams<{ city: string, keyword: string }>();

    const { pharmacyList, fetchNextPage } = useSearchRegionKeyword(city, keyword);

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

export default RegionKeywordPharmacyList;