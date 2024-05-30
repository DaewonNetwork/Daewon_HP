import React from "react"
import { PharmacyType } from "../types/pharmacys/Pharmacy.type";
import styles from "@/(FSD)/shareds/styles/PharmacyStyle.module.scss";
import { useRouter } from "next/navigation";

const PharmacyShared = ({ pharmacy }: { pharmacy: PharmacyType}) => {
    const router = useRouter();
    return (
        <div
        onMouseOver={e => { e.currentTarget.classList.add("bg-content3"); }}
        onMouseOut={e => { e.currentTarget.classList.remove("bg-content3"); }}
        onClick={_ => {
            router.push(`/pharmacy/${pharmacy.phId}`);
        }}
        className={styles.item}>
            <h1 className={"text-large font-medium"}>{ pharmacy.phName }</h1>
            <p className={`text-medium ${styles.text_add}`}>{ pharmacy.phAdd }</p>
            <p className={"text-medium"}>{ pharmacy.phTel }</p>
        </div>
    )
}

export default PharmacyShared;