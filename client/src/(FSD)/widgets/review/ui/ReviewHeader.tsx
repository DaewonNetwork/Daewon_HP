"use client";

import { useReadPharmacy } from "@/(FSD)/entities/pharmacy/api/useReadPharmacy";
import { useParams, useRouter } from "next/navigation";
import React, { useEffect } from "react";
import { PharmacyInfoType } from "@/(FSD)/shareds/types/pharmacys/PharmacyInfo.type";
import styles from "@/(FSD)/shareds/styles/ReviewStyle.module.scss";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import BackBtnShared from "@/(FSD)/shareds/ui/BackBtnShared";
import TextXlargeShared from "@/(FSD)/shareds/ui/TextXlargeShared";

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
        <div className={styles.header}>
            <InnerShared>
                <BackBtnShared />
                <TextXlargeShared>{pharmacyInfo.phName}</TextXlargeShared>
            </InnerShared>
        </div>
    );
};

export default ReviewHeader;