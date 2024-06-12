"use client";

import React, { useEffect } from "react";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";
import { useParams } from "next/navigation";
import { usePharmacyKeywordMap } from "@/(FSD)/features/pharmacy/api/usePharmacyKeywordMap";
import PharmacyMap from "@/(FSD)/entities/pharmacy/ui/PharmacyMap";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";

const KeywordMap = () => {
    const { keyword } = useParams<{ keyword: string }>();

    const { data, isError, isLoading, refetch } = usePharmacyKeywordMap(keyword);

    const pharmacyList: PharmacyType[] = data;
    

    useEffect(() => {
        refetch();
    }, [data, keyword]);

    if(isError) return <></>;
    if(isLoading) return <></>;

    return (
        <div className={styles.map}>
            <PharmacyMap pharmacyList={pharmacyList} />
        </div>
    );
};

export default KeywordMap;