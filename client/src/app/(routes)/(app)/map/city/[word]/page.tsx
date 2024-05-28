"use client";

import React, { useEffect } from "react";
import { useParams } from "next/navigation";
import { PharmacyType } from "@/(FSD)/shareds/types/Pharmacy.type";
import { Map, MapMarker } from "react-kakao-maps-sdk";
import { useSearchCity } from "@/(FSD)/entities/map/api/search/useSearchCity";
import MapSearcCityhForm from "@/(FSD)/entities/map/ui/MapCityForm";

const Page = () => {
    const { word } = useParams<{ word: string }>();

    const { data } = useSearchCity(word);

    const pharmacyList: PharmacyType[] = data;

    console.log(pharmacyList);

    useEffect(() => {

    }, [word]);

    if (!pharmacyList) return <></>;
    
    return (
        <>

        </>
    );
};

export default Page;