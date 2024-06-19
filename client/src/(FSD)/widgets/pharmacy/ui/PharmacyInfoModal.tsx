"use client";

import { usePharmacyRead } from "@/(FSD)/entities/pharmacy/api/usePharmacyRead";
import React, { useEffect } from "react";
import styles from "@/(FSD)/shareds/styles/Pharmacy.module.scss";
import ContainerShared from "@/(FSD)/shareds/ui/ContainerShared";
import TextLargeShared from "@/(FSD)/shareds/ui/TextLargeShared";
import { PharmacyInfoType } from "@/(FSD)/shareds/types/pharmacys/PharmacyInfo.type";
import TextMediumShared from "@/(FSD)/shareds/ui/TextMediumShared";
import { useRouter } from "next/navigation";
import StarShared from "@/(FSD)/shareds/ui/StarShared";
import PharmacyEnjoyBtn from "@/(FSD)/features/pharmacy/ui/PharmacyEnjoyBtn";
import useUserStore from "@/(FSD)/shareds/stores/useUserStore";

const PharmacyInfoModal = ({ phId, isOpen, setIsOpen }: { phId: number; isOpen: boolean; setIsOpen: React.Dispatch<React.SetStateAction<boolean>>; }) => {
    const { data, isError, refetch } = usePharmacyRead(phId);
    const { isLoggedIn } = useUserStore();

    const router = useRouter();

    useEffect(() => {
        refetch();
        setIsOpen(true);
    }, [phId]);

    const pharmacy: PharmacyInfoType = data;
    if (isError || !pharmacy || !isOpen) return <></>;

    return (
        <>
            <div
                onClick={_ => router.push(`/pharmacy?phId=${phId}`)}
                className={styles.pharmacy_info_modal}
            >
                <ContainerShared>
                    <div className={styles.modal_header}>
                        <TextLargeShared>{pharmacy.phName}</TextLargeShared>
                        {isLoggedIn && <PharmacyEnjoyBtn parentRefetch={refetch} phId={pharmacy.phId} defaultLikeActive={pharmacy.enjoy} />}
                    </div>
                    <div className={styles.modal_body}>
                        <TextMediumShared>{pharmacy.phAdd}</TextMediumShared>
                        <TextMediumShared>{pharmacy.phTel}</TextMediumShared>
                        <div className={styles.star_item}>
                            <StarShared isActive={true} />
                            <TextMediumShared>{pharmacy.starAvg}</TextMediumShared>
                        </div>
                    </div>
                </ContainerShared>
            </div>
        </>
    );
};

export default React.memo(PharmacyInfoModal);