"use client";

import React, { useEffect } from "react";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";
import PharmacyMapShared from "@/(FSD)/shareds/ui/PharmacyMapShared";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
import { usePharmacyNearMap } from "@/(FSD)/entities/pharmacy/api/usePharmacyNearMap";

const PharmacyNearMap = ({ lat, lng, isGeoPending }: { lat: number, lng: number, isGeoPending: boolean }) => {
    const { data, isPending, refetch } = usePharmacyNearMap(lat, lng);

    const pharmacyList: PharmacyType[] = data;

    useEffect(() => {
        refetch();
    }, [lat, lng, pharmacyList]);

    return (
        <div className={styles.map}>
            <PharmacyMapShared pharmacyList={pharmacyList} isPending={isPending || isGeoPending} />
        </div>
    );
};

export default PharmacyNearMap;