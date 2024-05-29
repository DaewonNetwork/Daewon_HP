import KeywordPharmacyList from "@/(FSD)/widgets/pharmacy/ui/KeywordPharmacyList";
import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";

const Page = () => {
    return (
        <div className={styles.container}>
            <KeywordPharmacyList />
        </div>
    );
};

export default Page;