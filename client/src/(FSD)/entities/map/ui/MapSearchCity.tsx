"use client";

import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import { cityList } from "../consts/cityList";
import { useRouter } from "next/navigation";

const MapSearchCity = () => {
    const router = useRouter();

    const onClick = (keyword: string) => {
        router.push(`/map/city/${keyword}`);
    };

    return (
        <nav className={styles.nav}>
            <div className={styles.city_container}>
                <div className={styles.city_list}>
                    {
                        cityList.map((item, index) => (
                            <React.Fragment key={index}>
                                <div onClick={_ => onClick(item)}>{item}</div>
                            </React.Fragment>
                        ))
                    }
                </div>
            </div>
        </nav>
    )
}

export default MapSearchCity;