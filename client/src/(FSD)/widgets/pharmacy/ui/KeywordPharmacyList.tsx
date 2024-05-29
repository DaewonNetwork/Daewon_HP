"use client";

import { useParams } from "next/navigation";
import React, { useEffect } from "react";
import { useInView } from "react-intersection-observer";
import styles from "@/(FSD)/shareds/styles/PharmacyStyle.module.scss";
import PharmacyList from "@/(FSD)/entities/pharmacy/ui/PharmacyList";
import { useSearchKeyword } from "@/(FSD)/features/pharmacy/api/search/useSearchKeyword";

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
        <div className={styles.container}>
            <PharmacyList pharmacyList={pharmacyList} />
            <div ref={ref} />
        </div>
    );
};

export default KeywordPharmacyList;