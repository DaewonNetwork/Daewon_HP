"use client";

import React, { useEffect } from "react";
import { useInView } from "react-intersection-observer";
import { useSearchAll } from "@/(FSD)/features/pharmacy/api/useSearchAll";
import PharmacyShared from "@/(FSD)/shareds/ui/PharmacyShared";

const AllPharmacyList = () => {
    const { pharmacyList, fetchNextPage } = useSearchAll();

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

export default AllPharmacyList;