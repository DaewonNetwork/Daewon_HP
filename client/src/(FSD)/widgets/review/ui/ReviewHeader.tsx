"use client";

import { usePharmacyRead } from "@/(FSD)/entities/pharmacy/api/usePharmacyRead";
import { useSearchParams } from "next/navigation";
import React, { useEffect } from "react";
import { PharmacyInfoType } from "@/(FSD)/shareds/types/pharmacys/PharmacyInfo.type";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import BackBtnShared from "@/(FSD)/shareds/ui/BackBtnShared";
import TextLargeShared from "@/(FSD)/shareds/ui/TextLargeShared";

const ReviewHeader = () => {
    const searchParams = useSearchParams();
    const phId = +searchParams.get("phId")!;
    const { data, isError, isPending, refetch } = usePharmacyRead(phId);

    const pharmacyInfo: PharmacyInfoType = data;

    useEffect(() => {
        refetch();
    }, [phId]);

    if (isError) return <></>;
    if (isPending) return <></>;

    return (
        <div className={styles.title_header}>
            <InnerShared>
                <BackBtnShared />
                <TextLargeShared>{pharmacyInfo.phName}</TextLargeShared>
            </InnerShared>
        </div>
    );
};

export default ReviewHeader;