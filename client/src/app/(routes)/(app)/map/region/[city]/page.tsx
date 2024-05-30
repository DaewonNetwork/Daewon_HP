import MapSearchRegionKeywordForm from "@/(FSD)/features/pharmacy/ui/MapSearchRegionKeywordForm";
import RegionPharmacyList from "@/(FSD)/widgets/pharmacy/ui/RegionPharmacyList";
import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import RegionMap from "@/(FSD)/widgets/map/ui/RegionMap";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";

const Page = () => {
    return (
        <>
            <AppHeader>
                <MapSearchRegionKeywordForm />
            </AppHeader>
            <RegionMap />
            <div className={styles.footer}>
                <ModalShared>
                    <RegionPharmacyList />
                </ModalShared>
            </div>
        </>
    );
};

export default Page;