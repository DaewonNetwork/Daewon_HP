import React from "react";

import MapSearchForm from "@/(FSD)/features/pharmacy/ui/MapSearchForm";
import PhRankEnjoy from "@/(FSD)/entities/pharmacy/ui/PhRankEnjoy";
import PhRankStar from "@/(FSD)/entities/pharmacy/ui/PhRankStar";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";

const Page = () => {
    return (
        <div>
            <AppHeader>
                <MapSearchForm />
            </AppHeader>
            <div className={styles.container}>
                <div className={styles.inner_section}>
                    <PhRankEnjoy />
                    <PhRankStar />
                </div>
            </div>
            <div className={styles.footer}>
                <AppFooter />
            </div>
        </div>
    )
}

export default Page;