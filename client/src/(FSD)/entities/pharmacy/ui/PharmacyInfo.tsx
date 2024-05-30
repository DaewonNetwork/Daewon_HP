"use client";

import React, { useEffect } from "react";
import { PharmacyType } from "@/(FSD)/shareds/types/Pharmacy.type";
import { useReadPharmacy } from "../api/useReadPharmacy";
import { useParams } from "next/navigation";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";

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
                <h1 className={"text-large"}>{pharmacy.phName}</h1>
            </div>
        </section>
    );
};

export default PharmacyInfo;