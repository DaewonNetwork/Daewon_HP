import NearPharmacyList from "@/(FSD)/widgets/pharmacy/ui/NearPharmacyList";
import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import NearMap from "@/(FSD)/widgets/map/ui/NearMap";

const Page = () => {
    return (
        <>
            <AppHeader>
                <></>
            </AppHeader>
            <NearMap />
            <div className={styles.footer}>
                <ModalShared>
                    <NearPharmacyList />
                </ModalShared>
            </div>
        </>
    );
};

export default Page;