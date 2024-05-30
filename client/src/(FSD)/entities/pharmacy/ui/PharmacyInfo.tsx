"use client";

import React, { useEffect } from "react";
import { PharmacyType } from "@/(FSD)/shareds/types/Pharmacy.type";
import { useReadPharmacy } from "../api/useReadPharmacy";
import { useParams } from "next/navigation";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import IconShared from "@/(FSD)/shareds/ui/IconShared";

const PharmacyInfo = () => {
    const { phId } = useParams<{ phId: string }>();

    const { data, isError, isLoading, refetch } = useReadPharmacy(Number(phId));

    console.log(data);

    const pharmacy: PharmacyType = data;


    useEffect(() => {
        refetch();
    }, [phId]);

    if (isError) return <></>;
    if (isLoading) return <></>;

    return (
        <section className={styles.ph_info}>
            <div className={styles.ph_box}>
                <h1 className={"text-large font-medium"}>{pharmacy.phName}</h1>
                <p className={`text-medium font-medium ${styles.ph_star}`}><IconShared iconType={"star"} /><span>{pharmacy.starAvg}</span></p>
            </div>
        </section>
    );
};

export default PharmacyInfo;