"use client";

import React, { useEffect } from "react";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";
import { useParams } from "next/navigation";
import PharmacyMap from "@/(FSD)/entities/pharmacy/ui/PharmacyMap";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
import { usePharmacyRegionKeywordMap } from "@/(FSD)/entities/pharmacy/api/usePharmacyMapRegionKeyword";

const RegionKeywordMap = () => {
    const { city, keyword } = useParams<{ city: string, keyword: string }>();

    const { data, isError, isLoading, refetch } = usePharmacyRegionKeywordMap(city, keyword);

    const pharmacyList: PharmacyType[] = data;

    useEffect(() => {
        refetch();
    }, [city, keyword]);

    if(isError) return <></>;
    if(isLoading) return <></>; 

    return (
        <div className={styles.map}>
            <PharmacyMap pharmacyList={pharmacyList} />
        </div>
    );
};

export default RegionKeywordMap;