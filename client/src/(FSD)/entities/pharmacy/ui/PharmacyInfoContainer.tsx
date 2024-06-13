"use client";

import { usePharmacyRead } from "@/(FSD)/entities/pharmacy/api/usePharmacyRead";
import ReviewContaner from "@/(FSD)/entities/review/ui/ReviewContaner";
import { PharmacyInfoType } from "@/(FSD)/shareds/types/pharmacys/PharmacyInfo.type";
import PharmacyInfo from "@/(FSD)/widgets/pharmacy/ui/PharmacyInfo";
import { notFound, useParams } from "next/navigation";
import React, { useEffect } from "react";
import Loading from "@/(FSD)/widgets/app/ui/Loading";

const PharmacyInfoContainer = () => {
    const { phId } = useParams<{ phId: string }>();
    const { data, isError, error, isPending, refetch } = usePharmacyRead(Number(phId));
    
    const pharmacy: PharmacyInfoType = data;

    useEffect(() => {
        refetch();
    }, [phId]);

    if(isPending) return <Loading />;
    console.log(JSON.parse(error?.message || ""));
    
    if(isError) return;

    return (
        <>
            <PharmacyInfo pharmacy={pharmacy} parentRefetch={refetch} />
            <ReviewContaner parentRefetch={refetch} />
        </>
    );
};

export default PharmacyInfoContainer;