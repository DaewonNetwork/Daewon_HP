"use client";

import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import React from "react";
import { useReadRankEnjoyPharmacys } from "../api/useReadRankEnjoyPharmacys";
import TextLargeShared from "@/(FSD)/shareds/ui/TextLargeShared";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
import PharmacyShared from "@/(FSD)/shareds/ui/PharmacyShared";
import styles from "@/(FSD)/shareds/styles/Pharmacy.module.scss";

const PharmacyRankEnjoyList = () => {
    const { data, refetch } = useReadRankEnjoyPharmacys();

    const pharmacyList: PharmacyType[] = data;

    if (!pharmacyList) return <></>;
    return (
        <div className={styles.pharmacy_rank_List}>
            <InnerShared>
                <TextLargeShared>사람들이 즐겨찾는 약국 TOP 10</TextLargeShared>
            </InnerShared>
            {
                pharmacyList.map((pharmacy, index) => {
                    if (index >= 10) return;
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

export default PharmacyRankEnjoyList;