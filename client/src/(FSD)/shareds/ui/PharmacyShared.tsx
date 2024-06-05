import React from "react"
import { PharmacyType } from "../types/pharmacys/Pharmacy.type";
import { useRouter } from "next/navigation";
import styles from "@/(FSD)/shareds/styles/Pharmacy.module.scss";
import TextLargeShared from "./TextLargeShared";
import TextMediumShared from "./TextMediumShared";
import ItemShared from "./ItemShared";
import PharmacyEnjoyBtn from "@/(FSD)/features/pharmacy/ui/PharmacyEnjoyBtn";

const PharmacyShared = ({ pharmacy, parentRefetch, rank }: { pharmacy: PharmacyType; parentRefetch?: any; rank?: string }) => {
    if (!pharmacy) return;
    const router = useRouter();

    return (
        <div
            onClick={_ => {
            router.push(`/pharmacy/${pharmacy.phId}`);
            }}
            className={styles.pharmacy_item}
        >
            <ItemShared>
                <div className={styles.top_item}>
                    <TextLargeShared>{rank} {pharmacy.phName}</TextLargeShared>
                    {!!localStorage.getItem("access_token") && <PharmacyEnjoyBtn parentRefetch={parentRefetch} phId={pharmacy.phId} defaultLikeActive={pharmacy.enjoy} />}
                </div>
                <div className={styles.btm_item}>
                    <TextMediumShared>{pharmacy.phAdd}</TextMediumShared>
                    <TextMediumShared>{pharmacy.phTel}</TextMediumShared>
                </div>
            </ItemShared>
        </div>
    )
}

export default PharmacyShared;