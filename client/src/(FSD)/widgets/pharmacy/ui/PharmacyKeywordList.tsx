"use client";

import { useParams } from "next/navigation";
import React, { useEffect } from "react";
import { useInView } from "react-intersection-observer";
import PharmacyShared from "@/(FSD)/shareds/ui/PharmacyShared";
import { usePharmacyKeywordSearch } from "@/(FSD)/entities/pharmacy/api/usePharmacyKeywordSearch";

const PharmacyKeywordList = () => {
    const { keyword } = useParams<{ keyword: string }>();

    const { pharmacyList, fetchNextPage, refetch } = usePharmacyKeywordSearch(keyword);

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

export default PharmacyKeywordList;