"use client";

import { useParams } from "next/navigation";
import React, { useEffect } from "react";
import { useInView } from "react-intersection-observer";
import { useSearchKeyword } from "@/(FSD)/features/pharmacy/api/useSearchKeyword";
import PharmacyShared from "@/(FSD)/shareds/ui/PharmacyShared";

const KeywordPharmacyList = () => {
    const { keyword } = useParams<{ keyword: string }>();

    const { pharmacyList, fetchNextPage } = useSearchKeyword(keyword);

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

export default KeywordPharmacyList;