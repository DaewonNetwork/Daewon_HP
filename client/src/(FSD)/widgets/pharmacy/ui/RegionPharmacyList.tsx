"use client";

import { useParams } from "next/navigation";
import React, { useEffect } from "react";
import { useInView } from "react-intersection-observer";
import styles from "@/(FSD)/shareds/styles/PharmacyStyle.module.scss";
import PharmacyList from "@/(FSD)/entities/pharmacy/ui/PharmacyList";
import { useSearchRegion } from "@/(FSD)/features/pharmacy/api/useSearchRegion";

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
        <div className={styles.container}>
            <PharmacyList pharmacyList={pharmacyList} />
            <div ref={ref} />
        </div>
    );
};

export default RegionPharmacyList;