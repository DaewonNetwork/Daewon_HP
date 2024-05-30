"use client";

import { useParams } from "next/navigation";
import React, { useEffect } from "react";
import PharmacyMap from "@/(FSD)/entities/map/ui/PharmacyMap";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
import { useMapRegionKeyword } from "@/(FSD)/features/map/api/useMapRegionKeyword";

const RegionKeywordMap = () => {
    const { city, keyword } = useParams<{ city: string, keyword: string }>();

    const { data, isError, isLoading, refetch } = useMapRegionKeyword(city, keyword);

    const pharmacyList: PharmacyType[] = data;

    useEffect(() => {
        refetch();
    }, [city, keyword]);

    if(isError) return <></>;
    if(isLoading) return <></>; 

    return (
        <div className={"map_container"}>
            <PharmacyMap pharmacyList={pharmacyList} />
        </div>
    );
};

export default RegionKeywordMap;