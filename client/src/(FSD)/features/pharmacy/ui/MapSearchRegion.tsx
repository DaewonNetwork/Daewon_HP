"use client";

import React from "react";
import { cityList } from "../consts/cityList";
import { useRouter } from "next/navigation";

const MapSearchRegion = () => {
    const router = useRouter();

    const onClick = (city: string) => {
        router.push(`/map/region/${city}`);
    };

    return (
        <>
            <div className={"text-medium"} onClick={_ => router.push("/")}>홈</div>
            <div className={"text-medium"} onClick={_ => router.push("/map/all")}>전체</div>
            <div className={"text-medium"} onClick={_ => router.push("/map/near")}>근처</div>
            {
                cityList.map((item, index) => (
                    <React.Fragment key={index}>
                        <div className={"text-medium"} onClick={_ => onClick(item)}>{item}</div>
                    </React.Fragment>
                ))
            }
        </>
    )
}

export default MapSearchRegion;