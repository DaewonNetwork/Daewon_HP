import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import MapSearchForm from "@/(FSD)/features/map/ui/MapSearchForm";
import SearchCityBar from "@/(FSD)/widgets/map/ui/SearchCityBar";

const Page = () => {
    return (
        <div className={styles.container}>
            <MapSearchForm />
            <SearchCityBar />
        </div>
    )
}

export default Page;