"use client";

import { useParams } from "next/navigation";
import React, { useEffect } from "react";
import { useInView } from "react-intersection-observer";
import styles from "@/(FSD)/shareds/styles/PharmacyStyle.module.scss";
import PharmacyList from "@/(FSD)/entities/pharmacy/ui/PharmacyList";
import { useSearchRegionKeyword } from "@/(FSD)/features/pharmacy/api/useSearchRegionKeyword";

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
        <div className={styles.container}>
            <PharmacyList pharmacyList={pharmacyList} />
            <div ref={ref} />
        </div>
    );
};

export default RegionKeywordPharmacyList;