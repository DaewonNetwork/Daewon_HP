"use client";

import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import React, { useEffect } from "react";
import { usePharmacyRankEnjoyListRead } from "../api/usePharmacyRankEnjoyListRead";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
import PharmacyItem from "@/(FSD)/widgets/pharmacy/ui/PharmacyItem";
import styles from "@/(FSD)/shareds/styles/Pharmacy.module.scss";
import TextXlargeShared from "@/(FSD)/shareds/ui/TextXlargeShared";

const PharmacyRankEnjoyList = () => {
    const { data, refetch } = usePharmacyRankEnjoyListRead();

    const pharmacyList: PharmacyType[] = data;
    
    useEffect(() => {
        refetch();
    }, [pharmacyList]);

    if (!pharmacyList) return <></>;
    return (
        <div className={styles.pharmacy_rank_List}>
            <InnerShared>
                <TextXlargeShared>사람들이 즐겨찾는 약국 TOP 10</TextXlargeShared >
            </InnerShared>
            {
                pharmacyList.map((pharmacy, index) => {
                    if (index >= 10) return;
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

export default PharmacyRankEnjoyList;