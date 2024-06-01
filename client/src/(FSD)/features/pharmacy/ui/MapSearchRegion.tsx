"use client";

import React from "react";
import { cityList } from "../consts/cityList";
import { useRouter } from "next/navigation";
import LinkBtnShared from "@/(FSD)/shareds/ui/LinkBtnShared";
import TextMediumShared from "@/(FSD)/shareds/ui/TextMediumShared";

const MapSearchRegion = () => {
    const router = useRouter();

    const onClick = (city: string) => {
        router.push(`/map/region/${city}`);
    };

    return (
        <nav>
            <LinkBtnShared size={"sm"} href={"/map/all"}><TextMediumShared>전체</TextMediumShared></LinkBtnShared>
            <LinkBtnShared size={"sm"} href={"/map/near"}><TextMediumShared>근처</TextMediumShared></LinkBtnShared>
            {
                cityList.map((city, index) => (
                    <React.Fragment key={index}>
                        <LinkBtnShared size={"sm"} href={`/map/region/${city}`}><TextMediumShared>{city}</TextMediumShared></LinkBtnShared>
                    </React.Fragment>
                ))
            }
        </nav>
    )
}

export default MapSearchRegion;