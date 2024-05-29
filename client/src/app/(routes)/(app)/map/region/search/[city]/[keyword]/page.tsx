import RegionKeywordPharmacyList from "@/(FSD)/widgets/pharmacy/ui/RegionKeywordPharmacyList";
import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import MapSearchRegionKeywordForm from "@/(FSD)/features/pharmacy/ui/MapSearchRegionKeywordForm";

const Page = () => {
    return (
        <div className={styles.container}>
            <MapSearchRegionKeywordForm />
            <RegionKeywordPharmacyList />
        </div>
    )
}

export default Page;