"use client";

import { useSearchRegion } from "@/(FSD)/features/pharmacy/api/search/useSearchRegion";
import PharmacyShared from "@/(FSD)/shareds/ui/PharmacyShared";
import { useParams } from "next/navigation";
import React, { useEffect } from "react";
import { useInView } from "react-intersection-observer";
import styles from "@/(FSD)/shareds/styles/PharmacyStyle.module.scss";

const PharmacyList = ({}) => {
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
            {
                pharmacyList.map((pharmacy, index) => (
                    <React.Fragment key={index}>
                        <PharmacyShared pharmacy={pharmacy} />
                    </React.Fragment>
                ))
            }
            <div ref={ref} />
        </div>
    );
};

export default PharmacyList;