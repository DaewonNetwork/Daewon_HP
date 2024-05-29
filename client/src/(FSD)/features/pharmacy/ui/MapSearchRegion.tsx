
"use client";

import React from "react";
import styles from "@/(FSD)/shareds/styles/AppStyle.module.scss";
import { cityList } from "../consts/cityList";
import { useRouter } from "next/navigation";

const MapSearchRegion = () => {
    const router = useRouter();

    const onClick = (city: string) => {
        router.push(`/map/region/${city}`);
    };

    return (
        <nav className={styles.nav}>
            <div className={styles.city_container}>
                <div className={styles.city_list}>
                    <div onClick={_ => router.push("/")}>홈</div>
                    <div onClick={_ => router.push("/map/all")}>전체</div>
                    <div onClick={_ => router.push("/map/near")}>근처</div>
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

export default MapSearchRegion;
