import React from 'react'
import { PharmacyType } from '../types/Pharmacy.type';
import styles from "@/(FSD)/shareds/styles/PharmacyStyle.module.scss";

const PharmacyShared = ({ pharmacy }: { pharmacy: PharmacyType}) => {
    return (
        <div className={styles.item}>
            <h1>{ pharmacy.phName }</h1>
            <p>{ pharmacy.phAdd }</p>
            <p>{ pharmacy.phTel }</p>
        </div>
    )
}

export default PharmacyShared;