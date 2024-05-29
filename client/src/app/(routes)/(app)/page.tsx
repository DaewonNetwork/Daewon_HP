import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";

import MapSearchForm from "@/(FSD)/features/pharmacy/ui/MapSearchForm";
import PhRankEnjoy from "@/(FSD)/entities/pharmacy/ui/PhRankEnjoy";
import PhRankStar from "@/(FSD)/entities/pharmacy/ui/PhRankStar";


const Page = () => {
    return (
        <div className={styles.container}>
            <MapSearchForm />
            <PhRankEnjoy />
            <PhRankStar />
        </div>
    )
}

export default Page;