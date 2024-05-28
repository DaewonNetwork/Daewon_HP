"use client";

import React, { useEffect } from "react";
import { useParams } from "next/navigation";
import { PharmacyType } from "@/(FSD)/shareds/types/Pharmacy.type";
import { useSearchCity } from "@/(FSD)/entities/map/api/search/useSearchCity";

const Page = () => {
    const { word } = useParams<{ word: string }>();

    const { data } = useSearchCity(word);

    useEffect(() => {

    }, [word]);

    console.log(data);
    

    // const pharmacyList: PharmacyType[] = data;

    // if (!pharmacyList) return <></>;
    
    return (
        <>

        </>
    );
};

export default Page;