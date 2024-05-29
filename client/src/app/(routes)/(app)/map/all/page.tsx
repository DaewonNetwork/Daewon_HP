import React from "react";
import AllPharmacyList from "@/(FSD)/widgets/pharmacy/ui/AllPharmacyList";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import MapSearchForm from "@/(FSD)/features/pharmacy/ui/MapSearchForm";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";

const Page = () => {
    return (
        <>
            <AppHeader>
                <MapSearchForm />
            </AppHeader>
            <div className={styles.container}>
                <ModalShared>
                     <AllPharmacyList />
                </ModalShared>
            </div>
        </>
    );
};

export default Page;