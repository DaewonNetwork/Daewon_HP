import PharmacyRankEnjoyList from "@/(FSD)/entities/pharmacy/ui/PharmacyRankEnjoyList";
import PharmacyRankStarList from "@/(FSD)/entities/pharmacy/ui/PharmacyRankStarList";
import React from "react";
import styles from "@/(FSD)/shareds/styles/Pharmacy.module.scss";

const PharmacyRankContainer = () => {
    return (
        <div className={styles.pharmacy_rank_container}>
            <PharmacyRankEnjoyList />
            <PharmacyRankStarList />
        </div>
    )
}

export default PharmacyRankContainer;