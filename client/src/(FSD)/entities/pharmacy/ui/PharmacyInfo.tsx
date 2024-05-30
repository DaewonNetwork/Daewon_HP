"use client";

import React, { useEffect } from "react";
import { useReadPharmacy } from "../api/useReadPharmacy";
import { useParams } from "next/navigation";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import IconShared from "@/(FSD)/shareds/ui/IconShared";
import { Chip } from "@nextui-org/chip";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";

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
                <h1 className={"text-xlarge font-semibold"}>{pharmacy.phName}</h1>
                <p className={"text-medium"}>{pharmacy.phAdd}</p>
                <div className={styles.chip_container}>
                    <Chip variant={"bordered"}><p className={`text-medium font-medium ${styles.ph_star}`}><IconShared iconType={"star"} /><span>{pharmacy.starAvg}</span></p></Chip>
                    <Chip variant={"bordered"}><p className={"text-medium font-medium"}>리뷰 {pharmacy.reviewIndex}</p></Chip>
                    <Chip variant={"bordered"}><p className={"text-medium font-medium"}>즐겨찾기 {pharmacy.enjoyIndex}</p></Chip>
                </div>

            </div>
        </section>
    );
};

export default PharmacyInfo;