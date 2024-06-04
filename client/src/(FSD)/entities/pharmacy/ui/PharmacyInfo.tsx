"use client";

import React, { useEffect, useState } from "react";
import { useReadPharmacy } from "../api/useReadPharmacy";
import { useParams } from "next/navigation";
import { PharmacyInfoType } from "@/(FSD)/shareds/types/pharmacys/PharmacyInfo.type";
import TextMediumShared from "@/(FSD)/shareds/ui/TextMediumShared";
import TextXlargeShared from "@/(FSD)/shareds/ui/TextXlargeShared";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import styles from "@/(FSD)/shareds/styles/Pharmacy.module.scss";
import { Chip } from "@nextui-org/chip";
import StarShared from "@/(FSD)/shareds/ui/StarShared";
import PharmacyEnjoyBtn from "@/(FSD)/features/pharmacy/ui/PharmacyEnjoyBtn";

const PharmacyInfo = () => {
    const { phId } = useParams<{ phId: string }>();

    const { data, isError, isLoading, refetch } = useReadPharmacy(Number(phId));

    const pharmacy: PharmacyInfoType = data;

    useEffect(() => {
        refetch();
    }, [phId]);

    if (isError) return <></>;
    if (isLoading) return <></>;

    return (
        <div className={styles.pharmacy_info}>
            <InnerShared>
                <div className={styles.pharmacy_header}>
                    <div className={styles.top_item}>
                        <TextXlargeShared>{pharmacy.phName}</TextXlargeShared>
                        <PharmacyEnjoyBtn parentRefetch={refetch} defaultLikeActive={pharmacy.enjoy} phId={pharmacy.phId} />
                    </div>
                    <div className={styles.btm_item}>
                        <TextMediumShared>{pharmacy.phAdd}</TextMediumShared>
                    </div>
                </div>
                <div className={styles.pharmacy_content}>
                    <Chip size={"lg"} variant={"bordered"}><StarShared isActive={true} /><TextMediumShared>{pharmacy.starAvg}</TextMediumShared></Chip>
                    <Chip size={"lg"} variant={"bordered"}><TextMediumShared>리뷰 {pharmacy.reviewIndex}</TextMediumShared></Chip>
                    <Chip size={"lg"} variant={"bordered"}><TextMediumShared>즐겨찾기 {pharmacy.enjoyIndex}</TextMediumShared></Chip>
                </div>
            </InnerShared>
        </div>
    );
};

export default PharmacyInfo;