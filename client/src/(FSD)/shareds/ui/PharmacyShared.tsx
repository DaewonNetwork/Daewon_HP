import React from "react"
import { PharmacyType } from "../types/pharmacys/Pharmacy.type";
import { useRouter } from "next/navigation";
import styles from "@/(FSD)/shareds/styles/Pharmacy.module.scss";
import TextLargeShared from "./TextLargeShared";
import TextMediumShared from "./TextMediumShared";
import ItemShared from "./ItemShared";

const PharmacyShared = ({ pharmacy }: { pharmacy: PharmacyType }) => {
    if (!pharmacy) return;
    const router = useRouter();

    console.log(pharmacy);
    

    return (
        <div
        onClick={_ => {
            router.push(`/pharmacy/${pharmacy.phId}`);
        }}
        className={styles.pharmacy_item}
        >
            <ItemShared>
                <TextLargeShared>{pharmacy.phName}</TextLargeShared>
                <TextMediumShared>{pharmacy.phAdd}</TextMediumShared>
                <TextMediumShared>{pharmacy.phTel}</TextMediumShared>
            </ItemShared>
        </div>
    )
}

export default PharmacyShared;