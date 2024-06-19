"use client";

import React from "react";
import styles from "@/(FSD)/shareds/styles/AppStyle.module.scss";
import { cityList } from "../consts/cityList";
import LinkBtnShared from "@/(FSD)/shareds/ui/LinkBtnShared";
import TextMediumShared from "@/(FSD)/shareds/ui/TextMediumShared";
import ContainerShared from "@/(FSD)/shareds/ui/ContainerShared";

const PharmacyRegionBar = () => {
    return (
        <nav className={styles.nav} data-slot={"nav"}>
            <ContainerShared>
                <LinkBtnShared size={"sm"} href={"/map/all"}><TextMediumShared>전체</TextMediumShared></LinkBtnShared>
                <LinkBtnShared size={"sm"} href={"/map/near"}><TextMediumShared>근처</TextMediumShared></LinkBtnShared>
                {
                    cityList.map(city => (
                        <React.Fragment key={city.cityId}>
                            <LinkBtnShared size={"sm"} href={`/map/region?city=${city.cityName}`}><TextMediumShared>{city.cityName}</TextMediumShared></LinkBtnShared>
                        </React.Fragment>
                    ))
                }
            </ContainerShared>
        </nav>
    )
}

export default PharmacyRegionBar;