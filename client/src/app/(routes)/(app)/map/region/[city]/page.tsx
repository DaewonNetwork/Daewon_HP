import MapSearchRegionKeywordForm from "@/(FSD)/features/pharmacy/ui/MapSearchRegionKeywordForm";
import RegionPharmacyList from "@/(FSD)/widgets/pharmacy/ui/RegionPharmacyList";
import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import RegionMap from "@/(FSD)/widgets/map/ui/RegionMap";

const Page = () => {
    return (
        <div className={styles.container}>
            <MapSearchRegionKeywordForm />
            <RegionMap />
            <RegionPharmacyList />
        </div>
    );
};

export default Page;