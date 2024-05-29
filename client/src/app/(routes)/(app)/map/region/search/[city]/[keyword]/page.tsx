import RegionKeywordPharmacyList from "@/(FSD)/widgets/pharmacy/ui/RegionKeywordPharmacyList";
import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import MapSearchRegionKeywordForm from "@/(FSD)/features/pharmacy/ui/MapSearchRegionKeywordForm";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";

const Page = () => {
    return (
        <>
            <AppHeader>
                <MapSearchRegionKeywordForm />
            </AppHeader>
            <div className={styles.container}>
                <ModalShared>
                    <RegionKeywordPharmacyList />
                </ModalShared>
            </div>
        </>
    )
}

export default Page;