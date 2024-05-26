import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import MapSearchForm from "@/(FSD)/features/map/ui/MapSearchForm";
import SlideCitySelectBar from "@/(FSD)/widgets/map/ui/SlideCitySelectBar";

const Page = () => {
    return (
        <div className={styles.container}>
            <MapSearchForm />
            <SlideCitySelectBar />
        </div>
    )
}

export default Page;