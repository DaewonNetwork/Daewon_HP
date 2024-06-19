"use client";

import React, { useEffect } from "react";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";
import { useSearchParams } from "next/navigation";
import { usePharmacyRegionMap } from "@/(FSD)/entities/pharmacy/api/usePharmacyRegionMap";
import PharmacyMapShared from "@/(FSD)/shareds/ui/PharmacyMapShared";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";

const PharmacyRegionMap = () => {
    const searchParams = useSearchParams();
    const city = searchParams.get("city")!;

    const { data, isError, isPending, refetch } = usePharmacyRegionMap(city);

    const pharmacyList: PharmacyType[] = data;
    useEffect(() => {
        refetch();
    }, [data, city]);

    if (!pharmacyList) return <></>;

    return (
        <div className={styles.map}>
            <PharmacyMapShared pharmacyList={pharmacyList} isPending={isPending} />
        </div>
    );
};

export default PharmacyRegionMap;