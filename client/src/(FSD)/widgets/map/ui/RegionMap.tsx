"use client";

import { useParams } from "next/navigation";
import React, { useEffect } from "react";
import { useMapRegion } from "@/(FSD)/features/map/api/useMapRegion";
import PharmacyMap from "@/(FSD)/entities/map/ui/PharmacyMap";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";

const RegionMap = () => {
    const { city } = useParams<{ city: string }>();

    const { data, isError, isLoading, refetch } = useMapRegion(city);

    const pharmacyList: PharmacyType[] = data;
    

    useEffect(() => {
        refetch();
    }, [data, city]);
    
    if(isError) return <></>;
    if(isLoading) return <></>;

    return (
        <div className={"map_container"}>
            <PharmacyMap pharmacyList={pharmacyList} />
        </div>
    );
};

export default RegionMap;