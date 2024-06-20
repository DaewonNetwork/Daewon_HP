"use client";

import React, { useEffect } from "react";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import { usePharmacyRankStarListRead } from "../api/usePharmacyRankStarListRead";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
import PharmacyItem from "@/(FSD)/widgets/pharmacy/ui/PharmacyItem";
import TextXlargeShared from "@/(FSD)/shareds/ui/TextXlargeShared";
import styles from "@/(FSD)/shareds/styles/Pharmacy.module.scss";

const PharmacyRankStarList = () => {
    const { data, refetch } = usePharmacyRankStarListRead();

    const pharmacyList: PharmacyType[] = data;

    useEffect(() => {
        refetch();
    }, [pharmacyList]);
    
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
                        <React.Fragment key={pharmacy.phId}>
                            <PharmacyItem rank={`${index + 1}위`} parentRefetch={refetch} pharmacy={pharmacy} />
                        </React.Fragment>
                    )
                })
            }
        </div>
    )
}

export default PharmacyRankStarList;