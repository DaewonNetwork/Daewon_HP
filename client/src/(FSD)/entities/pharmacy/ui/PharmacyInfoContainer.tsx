"use client";

import { usePharmacyRead } from "@/(FSD)/entities/pharmacy/api/usePharmacyRead";
import ReviewContaner from "@/(FSD)/entities/review/ui/ReviewContaner";
import { PharmacyInfoType } from "@/(FSD)/shareds/types/pharmacys/PharmacyInfo.type";
import PharmacyInfo from "@/(FSD)/widgets/pharmacy/ui/PharmacyInfo";
import { notFound, useSearchParams } from "next/navigation";
import React, { useEffect } from "react";
import Loading from "@/(FSD)/widgets/app/ui/Loading";

const PharmacyInfoContainer = () => {
    const searchParams = useSearchParams();
    const phId = +searchParams.get("phId")!;

    const { data, isError, error, isPending, refetch } = usePharmacyRead(phId);
    
    const pharmacy: PharmacyInfoType = data;

    useEffect(() => {
        refetch();
    }, [phId]);
    
    if(isError) notFound();
    if(isPending) return <Loading />;

    return (
        <>
            <PharmacyInfo pharmacy={pharmacy} parentRefetch={refetch} />
            <ReviewContaner parentRefetch={refetch} />
        </>
    );
};

export default PharmacyInfoContainer;