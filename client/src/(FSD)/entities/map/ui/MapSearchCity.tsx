"use client";

import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import TextTitleShared from "@/(FSD)/shareds/ui/TextTitleShared";
import { cityList } from "../consts/cityList";
import { Chip } from "@nextui-org/chip";

const MapSearchCity = () => {
    return (
        <section className={styles.section}>
            <TextTitleShared>지역별 검색</TextTitleShared>

            <div className={styles.city_list}>
                {
                    cityList.map((item, index) => (
                        <React.Fragment key={index}>
                            <Chip>{item}</Chip>
                        </React.Fragment>
                    ))
                }
            </div>
        </section>
    )
}

export default MapSearchCity;