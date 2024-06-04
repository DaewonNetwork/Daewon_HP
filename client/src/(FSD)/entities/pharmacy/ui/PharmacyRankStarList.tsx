"use client";

import React from "react";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import { useReadRankStarPharmacys } from "../api/useReadRankStarPharmacys";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
import PharmacyShared from "@/(FSD)/shareds/ui/PharmacyShared";
import TextXlargeShared from "@/(FSD)/shareds/ui/TextXlargeShared";
import styles from "@/(FSD)/shareds/styles/Pharmacy.module.scss";

const PharmacyRankStarList = () => {
    const { data, refetch } = useReadRankStarPharmacys();

    const pharmacyList: PharmacyType[] = data;
    
    if (!pharmacyList) return <></>;

    return (
        <div className={styles.pharmacy_rank_List}>
            <InnerShared>
                <TextXlargeShared>실시간 평점 순위</TextXlargeShared>
            </InnerShared>
            {
                pharmacyList.map((pharmacy, index) => {
                    if(index >= 10) return;
                    return (
                        <React.Fragment key={index}>
                            <PharmacyShared rank={`${index + 1}위`} parentRefetch={refetch} pharmacy={pharmacy} />
                        </React.Fragment>
                    )
                })
            }
        </div>
    )
}

export default PharmacyRankStarList;