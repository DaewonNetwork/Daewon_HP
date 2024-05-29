import React from 'react'
import { PharmacyType } from '../types/Pharmacy.type';
import styles from "@/(FSD)/shareds/styles/PharmacyStyle.module.scss";

const PharmacyShared = ({ pharmacy }: { pharmacy: PharmacyType}) => {
    return (
        <div
        onMouseOver={e => { e.currentTarget.classList.add("bg-content3"); }}
        onMouseOut={e => { e.currentTarget.classList.remove("bg-content3"); }}
        className={styles.item}>
            <h1 className={"text-large font-medium"}>{ pharmacy.phName }</h1>
            <p className={`text-medium ${styles.text_add}`}>{ pharmacy.phAdd }</p>
            <p className={"text-medium"}>{ pharmacy.phTel }</p>
        </div>
    )
}

export default PharmacyShared;