import NearPharmacyList from "@/(FSD)/widgets/pharmacy/ui/NearPharmacyList";
import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";

const Page = () => {
    return (
        <>
            <AppHeader>
                <></>
            </AppHeader>
            <div className={styles.container}>
                <ModalShared>
                    <NearPharmacyList />
                </ModalShared>
            </div>
        </>
    );
};

export default Page;