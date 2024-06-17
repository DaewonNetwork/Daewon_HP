"use client";

import { usePharmacyRead } from "@/(FSD)/entities/pharmacy/api/usePharmacyRead";
import React, { useEffect } from "react";
import styles from "@/(FSD)/shareds/styles/Pharmacy.module.scss";
import ContainerShared from "@/(FSD)/shareds/ui/ContainerShared";
import TextLargeShared from "@/(FSD)/shareds/ui/TextLargeShared";
import { PharmacyInfoType } from "@/(FSD)/shareds/types/pharmacys/PharmacyInfo.type";
import TextMediumShared from "@/(FSD)/shareds/ui/TextMediumShared";

const PharmacyInfoModal = ({ phId, isOpen, setIsOpen }: { phId: number; isOpen: boolean; setIsOpen: React.Dispatch<React.SetStateAction<boolean>>; }) => {
    const { data, isError, refetch } = usePharmacyRead(phId);

    useEffect(() => {
        refetch();
        setIsOpen(true);
    }, [phId]);

    const pharmacy: PharmacyInfoType = data;
    console.log(pharmacy);
    if (isError || !pharmacy || !isOpen) return <></>;

    return (
        <>
            <div className={styles.pharmacy_info_modal}>
                <ContainerShared>
                    <TextLargeShared>{pharmacy.phName}</TextLargeShared>
                    <TextMediumShared>{pharmacy.phAdd}</TextMediumShared>
                </ContainerShared>
            </div>
        </>
    );
};

export default React.memo(PharmacyInfoModal);