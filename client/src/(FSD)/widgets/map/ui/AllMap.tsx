"use client";

import React from "react";
import PharmacyMap from "@/(FSD)/entities/map/ui/PharmacyMap";
import { PharmacyType } from "@/(FSD)/shareds/types/Pharmacy.type";
import { useMapAll } from "@/(FSD)/features/map/api/useMapAll";

const RegionAll = () => {
    const { data, isError, isLoading, refetch } = useMapAll();

    const pharmacyList: PharmacyType[] = data;

    if(isError) return <></>;
    if(isLoading) return <></>;

    return (
        <div style={{ width: "100%", height: "100%" }}>
            <PharmacyMap pharmacyList={pharmacyList} />
        </div>
    );
};

export default RegionAll;