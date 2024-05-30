"use client";

import React, { useEffect } from "react";
import PharmacyMap from "@/(FSD)/entities/map/ui/PharmacyMap";
import { PharmacyType } from "@/(FSD)/shareds/types/Pharmacy.type";
import { useMapAll } from "@/(FSD)/features/map/api/useMapAll";

const AllMap = () => {
    const { data, isError, isLoading, refetch } = useMapAll();

    const pharmacyList: PharmacyType[] = data;
    if(isError) return <></>;
    if(isLoading) return <></>;

    return (
        <div className={"map_container"}>
            <PharmacyMap pharmacyList={pharmacyList} />
        </div>
    );
};

export default AllMap;