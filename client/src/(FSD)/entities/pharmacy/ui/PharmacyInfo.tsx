"use client";

import React, { useEffect } from "react";
import { useReadPharmacy } from "../api/useReadPharmacy";
import { useParams } from "next/navigation";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import IconShared from "@/(FSD)/shareds/ui/IconShared";
import { Chip } from "@nextui-org/chip";
import { PharmacyInfoType } from "@/(FSD)/shareds/types/pharmacys/PharmacyInfo.type";
import useUserStore from "@/(FSD)/shareds/stores/useUserStore";
import { Button } from "@nextui-org/button";
import Link from "next/link";

const PharmacyInfo = () => {
    const { phId } = useParams<{ phId: string }>();

    const { data, isError, isLoading, refetch } = useReadPharmacy(Number(phId));

    const user = useUserStore().user;

    console.log(user);

    const pharmacy: PharmacyInfoType = data;

    useEffect(() => {
        refetch();
    }, [phId]);

    useEffect(() => {

    }, [user]);

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
            <div className={styles.review_box}>
                <h2 className={"text-large font-medium"}>리뷰</h2>
                <Button as={Link} href={`/review/create/${phId}`} color={"primary"} size={"lg"} fullWidth>리뷰 작성하기</Button>
            </div>
        </section>
    );
};

export default PharmacyInfo;