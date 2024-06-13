"use client";

import React, { useEffect, useState } from "react";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";
import PharmacyMapShared from "@/(FSD)/shareds/ui/PharmacyMapShared";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
import { usePharmacyNearMap } from "@/(FSD)/entities/pharmacy/api/usePharmacyNearMap";

const PharmacyNearMap = () => {
    const [lat, setLat] = useState<number>(0);
    const [lng, setLng] = useState<number>(0);

    const [isGeoError, setIsGeoError] = useState<boolean>(false);
    const [isGeoPending, setIsGeoPending] = useState<boolean>(true);

    const { data, isError, isPending, refetch } = usePharmacyNearMap(lat, lng);

    useEffect(() => {
        if ("geolocation" in navigator) {
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    setLat(position.coords.latitude);
                    setLng(position.coords.longitude);

                }, (error) => {
                    setIsGeoError(true);
                    setIsGeoPending(false);
                }
            )
        } else {
            setIsGeoError(true);
            setIsGeoPending(false);
        }

        refetch();
    }, [data, lat, lng]);

    const pharmacyList: PharmacyType[] = data;
    
    return (
        <div className={styles.map}>
            <PharmacyMapShared pharmacyList={pharmacyList} isPending={isGeoPending && isPending} />
        </div>
    );
};

export default PharmacyNearMap;