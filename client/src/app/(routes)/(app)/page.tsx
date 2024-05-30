import React from "react";

import MapSearchForm from "@/(FSD)/features/pharmacy/ui/MapSearchForm";
import PhRankEnjoy from "@/(FSD)/entities/pharmacy/ui/PhRankEnjoy";
import PhRankStar from "@/(FSD)/entities/pharmacy/ui/PhRankStar";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";

const Page = () => {
    return (
        <>
            <AppHeader>
                <MapSearchForm />
            </AppHeader>
            <div className={styles.container}>
                <div className={styles.nav_inner}>
                    <PhRankEnjoy />
                    <PhRankStar />
                </div>
            </div>
            <footer className={"footer"}>
                <AppFooter />
            </footer>
        </>
    )
}

export default Page;