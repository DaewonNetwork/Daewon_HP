"use client";

import React, { useEffect, useState } from "react";
import { useInView } from "react-intersection-observer";
import { usePharmacyAllSearch } from "@/(FSD)/entities/pharmacy/api/usePharmacyAllSearch";
import PharmacyShared from "@/(FSD)/shareds/ui/PharmacyShared";

const PharmacyAllList = () => {
    const { pharmacyList, fetchNextPage, refetch } = usePharmacyAllSearch();

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
            <div ref={ref} />
        </>
    );
};

export default PharmacyAllList;