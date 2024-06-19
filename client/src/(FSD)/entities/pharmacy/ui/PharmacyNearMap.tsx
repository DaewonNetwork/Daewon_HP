"use client";

import React, { useEffect } from "react";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";
import PharmacyMapShared from "@/(FSD)/shareds/ui/PharmacyMapShared";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
import { usePharmacyNearMap } from "@/(FSD)/entities/pharmacy/api/usePharmacyNearMap";
import { useMapLoad } from "@/(FSD)/shareds/hooks/useMapLoad";
import { notFound } from "next/navigation";

const PharmacyNearMap = ({ lat, lng, isGeoPending }: { lat: number, lng: number, isGeoPending: boolean }) => {
    const { data, isPending, isError, refetch } = usePharmacyNearMap(lat, lng);

    const pharmacyList: PharmacyType[] = data;

    useMapLoad([lat, lng, pharmacyList]);
    
    useEffect(() => {
        refetch();
    }, [lat, lng, pharmacyList]);

    if(isError && (pharmacyList && (!pharmacyList[0]))) return notFound();

    return (
        <div className={styles.map}>
            <PharmacyMapShared pharmacyList={pharmacyList} isPending={isPending || isGeoPending} />
        </div>
    );
};

export default PharmacyNearMap;