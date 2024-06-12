"use client";

import React, { useEffect, useState } from "react";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";
import PharmacyMap from "@/(FSD)/entities/pharmacy/ui/PharmacyMap";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
import { usePharmacyNearMap } from "@/(FSD)/entities/pharmacy/api/usePharmacyNearMap";

const PharmacyNearMap = () => {
    const [lat, setLat] = useState<number>(0);
    const [lng, setLng] = useState<number>(0);

    const [isGeoError, setIsGeoError] = useState<boolean>(false);

    const { data, isError, isPending, refetch } = usePharmacyNearMap(lat, lng);

    useEffect(() => {
        if ("geolocation" in navigator) {
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    setLat(position.coords.latitude);
                    setLng(position.coords.longitude);

                }
            )
        } else {
            setIsGeoError(true);
        }

        refetch();
    }, [data, lat, lng]);

    const pharmacyList: PharmacyType[] = data;
    
    if(isGeoError) return <></>;
    if(isError) return <></>;
    if(isPending) return <></>;
    if((!pharmacyList) || (!pharmacyList[0])) return <></>;


    return (
        <div className={styles.map}>
            <PharmacyMap pharmacyList={pharmacyList} />
        </div>
    );
};

export default PharmacyNearMap;