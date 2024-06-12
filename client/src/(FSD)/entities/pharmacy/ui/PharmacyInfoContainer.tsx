"use client";

import { usePharmacyRead } from "@/(FSD)/entities/pharmacy/api/usePharmacyRead";
import PharmacyInfo from "@/(FSD)/entities/pharmacy/ui/PharmacyInfo";
import ReviewContaner from "@/(FSD)/entities/review/ui/ReviewContaner";
import { PharmacyInfoType } from "@/(FSD)/shareds/types/pharmacys/PharmacyInfo.type";
import { useParams } from "next/navigation";
import React, { useEffect } from "react";

const PharmacyInfoContainer = () => {
    const { phId } = useParams<{ phId: string }>();
    const { data, isError, isPending, refetch } = usePharmacyRead(Number(phId));
    const pharmacy: PharmacyInfoType = data;

    useEffect(() => {
        refetch();
    }, [phId]);

    if (isError) return <></>;

    if (isPending) return <></>;

    return (
        <>
            <PharmacyInfo pharmacy={pharmacy} parentRefetch={refetch} />
            <ReviewContaner parentRefetch={refetch} />
        </>
    );
};

export default PharmacyInfoContainer;