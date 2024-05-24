import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import MapSearchForm from "@/(FSD)/features/map/ui/MapSearchForm";

const Page = () => {
    return (
        <div className={styles.container}>
            <MapSearchForm />
        </div>
    )
}

export default Page;