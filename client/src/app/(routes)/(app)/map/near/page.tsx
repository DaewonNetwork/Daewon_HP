import NearPharmacyList from "@/(FSD)/widgets/pharmacy/ui/NearPharmacyList";
import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";

const Page = () => {
    return (
        <div className={styles.container}>
            <NearPharmacyList />
        </div>
    );
};

export default Page;