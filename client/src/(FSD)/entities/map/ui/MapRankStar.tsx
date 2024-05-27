import React from "react";
import TextTitleShared from "@/(FSD)/shareds/ui/TextTitleShared";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";

const MapRankStar = () => {
    return (
        <section className={styles.section}>
            <TextTitleShared>실시간 평점 순위</TextTitleShared>
        </section>
    )
}

export default MapRankStar;