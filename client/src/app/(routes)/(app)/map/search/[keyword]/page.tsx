import KeywordPharmacyList from "@/(FSD)/widgets/pharmacy/ui/KeywordPharmacyList";
import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import MapSearchRegionKeywordForm from "@/(FSD)/features/pharmacy/ui/MapSearchRegionKeywordForm";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";

const Page = () => {
    return (
        <>
            <AppHeader>
                <MapSearchRegionKeywordForm />
            </AppHeader>
            <div className={styles.container}>
                <ModalShared>
                    <KeywordPharmacyList />
                </ModalShared>
            </div>
        </>
    );
};

export default Page;