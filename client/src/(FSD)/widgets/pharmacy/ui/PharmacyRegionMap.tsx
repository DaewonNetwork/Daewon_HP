"use client";

import React, { useEffect } from "react";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";
import { useParams } from "next/navigation";
import { usePharmacyRegionMap } from "@/(FSD)/entities/pharmacy/api/usePharmacyRegionMap";
import PharmacyMap from "@/(FSD)/entities/pharmacy/ui/PharmacyMap";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";

const PharmacyRegionMap = () => {
    const { city } = useParams<{ city: string }>();

    const { data, isError, isLoading, refetch } = usePharmacyRegionMap(city);

    const pharmacyList: PharmacyType[] = data;
    

    useEffect(() => {
        refetch();
    }, [data, city]);
    
    if(isError) return <></>;
    if(isLoading) return <></>;

    return (
        <div className={styles.map}>
            <PharmacyMap pharmacyList={pharmacyList} />
        </div>
    );
};

export default PharmacyRegionMap;