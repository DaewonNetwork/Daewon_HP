"use client";

import React from "react";
import { useMapSearch } from "../api/useMapSearch";

const PharmacyListEntitie = ({ keyword }: { keyword: string }) => {
    const { data } = useMapSearch(keyword);    

    console.log(data);
    

    return (
        <>

        </>
    );
};

export default PharmacyListEntitie;