"use client";

import React, { useEffect } from "react";
import { PharmacyType } from "../../../shareds/types/Pharmacy.type";
import { useReadPharmacy } from "../api/useReadPharmacy";
import { useParams } from "next/navigation";

const PharmacyInfo = () => {
    const { phId } = useParams<{ phId: string }>();

    const { data, isError, isLoading, refetch } = useReadPharmacy(Number(phId));

    console.log(data);
    
    const pharmacy: PharmacyType = data;
    

    useEffect(() => {
        refetch();
    }, [phId]);

    if(isError) return <></>;
    if(isLoading) return <></>;

    return (
        <div>
            {pharmacy.phName}
        </div>
    );
};

export default PharmacyInfo;