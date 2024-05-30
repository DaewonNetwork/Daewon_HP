import React from "react";
import AllPharmacyList from "@/(FSD)/widgets/pharmacy/ui/AllPharmacyList";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import MapSearchForm from "@/(FSD)/features/pharmacy/ui/MapSearchForm";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import AllMap from "@/(FSD)/widgets/map/ui/AllMap";

const Page = () => {
    return (
        <>
            <AppHeader>
                <MapSearchForm />
            </AppHeader>
            <AllMap />
            <div className={styles.footer}>
                <ModalShared>
                    <AllPharmacyList />
                </ModalShared>
            </div>
        </>
    );
};

export default Page;