"use client";

import { useReadPharmacy } from "@/(FSD)/entities/pharmacy/api/useReadPharmacy";
import { useParams, useRouter } from "next/navigation";
import React, { useEffect } from "react";
import { PharmacyInfoType } from "@/(FSD)/shareds/types/pharmacys/PharmacyInfo.type";
import { Button } from "@nextui-org/button";
import IconShared from "@/(FSD)/shareds/ui/IconShared";

const ReviewHeader = () => {
    const { phId } = useParams<{ phId: string }>();
    const { data, isError, isLoading, refetch } = useReadPharmacy(Number(phId));

    const router = useRouter();

    const pharmacyInfo: PharmacyInfoType = data;

    useEffect(() => {
        refetch();
    }, [phId]);

    if (isError) return <></>;
    if (isLoading) return <></>;

    return (
        <>
            <Button onClick={_ => router.back()} variant={"light"} isIconOnly endContent={<IconShared iconType={"left"} />} />
            <h1 className={"text-large font-semibold"}>{pharmacyInfo.phName}</h1>
        </>
    );
};

export default ReviewHeader;