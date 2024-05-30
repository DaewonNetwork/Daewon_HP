import PharmacyInfo from "@/(FSD)/entities/pharmacy/ui/PharmacyInfo";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";

const Page = () => {
    return (
        <>
            <AppHeader isSearchRegion={false} />
            <div className={styles.container}>
                <div className={styles.container_inner}>
                    <PharmacyInfo />
                </div>
            </div>
            <footer className={"footer"}>
                <AppFooter />
            </footer>
        </>
    );
};

export default Page;