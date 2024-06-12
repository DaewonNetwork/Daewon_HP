"use client";

import { useParams } from "next/navigation";
import React, { useEffect } from "react";
import { useInView } from "react-intersection-observer";
import { usePharmacyRegionKeywordSearch } from "@/(FSD)/entities/pharmacy/api/usePharmacyRegionKeywordSearch";
import PharmacyShared from "@/(FSD)/shareds/ui/PharmacyShared";

const PharmacyRegionKeywordList = () => {
    const { city, keyword } = useParams<{ city: string, keyword: string }>();

    const { pharmacyList, fetchNextPage, refetch } = usePharmacyRegionKeywordSearch(city, keyword);

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

export default PharmacyRegionKeywordList;