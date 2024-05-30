"use client";

import React, { useEffect, useState } from "react";
import PharmacyMap from "@/(FSD)/entities/map/ui/PharmacyMap";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
import { useMapNear } from "@/(FSD)/features/map/api/useMapNear";

const NearMap = () => {
    const [lat, setLat] = useState<number>(0);
    const [lng, setLng] = useState<number>(0);

    const [isGeoError, setIsGeoError] = useState<boolean>(false);

    const { data, isError, isLoading, refetch } = useMapNear(lat, lng);

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
    if(isLoading) return <></>;
    if((!pharmacyList) || (!pharmacyList[0])) return <></>;


    return (
        <div className={"map_container"}>
            <PharmacyMap pharmacyList={pharmacyList} />
        </div>
    );
};

export default NearMap;