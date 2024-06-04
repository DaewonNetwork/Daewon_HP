"use client";

import { useReadPharmacy } from "@/(FSD)/entities/pharmacy/api/useReadPharmacy";
import PharmacyInfo from "@/(FSD)/entities/pharmacy/ui/PharmacyInfo";
import ReviewContaner from "@/(FSD)/entities/review/ui/ReviewContaner";
import { PharmacyInfoType } from "@/(FSD)/shareds/types/pharmacys/PharmacyInfo.type";
import { useParams } from "next/navigation";
import React, { useEffect } from "react";

const PharmacyInfoContainer = () => {
    const { phId } = useParams<{ phId: string }>();
    const { data, isError, isLoading, refetch } = useReadPharmacy(Number(phId));
    const pharmacy: PharmacyInfoType = data;

    useEffect(() => {
        refetch();
    }, [phId]);

    if (isError) return <></>;

    if (isLoading) return <></>;

    return (
        <>
            <PharmacyInfo pharmacy={pharmacy} parentRefetch={refetch} />
            <ReviewContaner parentRefetch={refetch} />
        </>
    );
};

export default PharmacyInfoContainer;