"use client";

import { useReadPharmacy } from "@/(FSD)/entities/pharmacy/api/useReadPharmacy";
import { useParams } from "next/navigation";
import React, { useEffect } from "react";
import { PharmacyInfoType } from "@/(FSD)/shareds/types/pharmacys/PharmacyInfo.type";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import BackBtnShared from "@/(FSD)/shareds/ui/BackBtnShared";
import TextLargeShared from "@/(FSD)/shareds/ui/TextLargeShared";

const ReviewHeader = () => {
    const { phId } = useParams<{ phId: string }>();
    const { data, isError, isLoading, refetch } = useReadPharmacy(Number(phId));

    const pharmacyInfo: PharmacyInfoType = data;

    useEffect(() => {
        refetch();
    }, [phId]);

    if (isError) return <></>;
    if (isLoading) return <></>;

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