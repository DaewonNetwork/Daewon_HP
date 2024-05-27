import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import MapSearchForm from "@/(FSD)/entities/map/ui/MapSearchForm";
import MapSearchCity from "@/(FSD)/entities/map/ui/MapSearchCity";
import MapRankStar from "@/(FSD)/entities/map/ui/MapRankStar";
import MapRankEnjoy from "@/(FSD)/entities/map/ui/MapRankEnjoy";

const Page = () => {
    return (
        <div className={styles.container}>
            <MapSearchForm />
            <MapSearchCity />
            <MapRankStar />
            <MapRankEnjoy />
        </div>
    )
}

export default Page;