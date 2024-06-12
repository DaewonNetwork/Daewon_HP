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
                <LinkBtnShared size={"sm"} href={"/pharmacy/all"}><TextMediumShared>전체</TextMediumShared></LinkBtnShared>
                <LinkBtnShared size={"sm"} href={"/pharmacy/near"}><TextMediumShared>근처</TextMediumShared></LinkBtnShared>
                {
                    cityList.map((city, index) => (
                        <React.Fragment key={index}>
                            <LinkBtnShared size={"sm"} href={`/pharmacy/region/${city}`}><TextMediumShared>{city}</TextMediumShared></LinkBtnShared>
                        </React.Fragment>
                    ))
                }
            </ContainerShared>
        </nav>
    )
}

export default PharmacyRegionBar;