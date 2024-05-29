import React from "react";
import AllPharmacyList from "@/(FSD)/widgets/pharmacy/ui/AllPharmacyList";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import MapSearchForm from "@/(FSD)/features/pharmacy/ui/MapSearchForm";

const Page = () => {
    return (
        <div className={styles.container}>
            <MapSearchForm />
            <AllPharmacyList />
        </div>
    );
};

export default Page;