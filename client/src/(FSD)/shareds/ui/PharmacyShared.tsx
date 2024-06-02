import React from "react"
import { PharmacyType } from "../types/pharmacys/Pharmacy.type";
import { useRouter } from "next/navigation";
import styles from "@/(FSD)/shareds/styles/Pharmacy.module.scss";
import TextLargeShared from "./TextLargeShared";
import TextMediumShared from "./TextMediumShared";
import ItemShared from "./ItemShared";
import PharmacyEnjoyBtn from "@/(FSD)/features/pharmacy/ui/PharmacyEnjoyBtn";

const PharmacyShared = ({ pharmacy }: { pharmacy: PharmacyType }) => {
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
                <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: 10 }}>
                    <TextLargeShared>{pharmacy.phName}</TextLargeShared>
                    <PharmacyEnjoyBtn phId={pharmacy.phId} />
                </div>
                <TextMediumShared>{pharmacy.phAdd}</TextMediumShared>
                <TextMediumShared>{pharmacy.phTel}</TextMediumShared>
            </ItemShared>
        </div>
    )
}

export default PharmacyShared;