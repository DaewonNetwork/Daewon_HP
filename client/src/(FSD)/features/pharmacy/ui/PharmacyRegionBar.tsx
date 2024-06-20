"use client";

import React, { MouseEvent, useRef, useState } from "react";
import styles from "@/(FSD)/shareds/styles/AppStyle.module.scss";
import { cityList } from "../consts/cityList";
import LinkBtnShared from "@/(FSD)/shareds/ui/LinkBtnShared";
import TextMediumShared from "@/(FSD)/shareds/ui/TextMediumShared";
import Link from "next/link";

const PharmacyRegionBar = () => {
    const containerRef = useRef<HTMLDivElement>(null);
    const [dragging, setDragging] = useState<boolean>(false);
    const [scrollLeft, setScrollLeft] = useState<number>(0);
    const [clickPoint, setClickPoint] = useState<number>(0);


    const handleMouseDownEvent = (e: MouseEvent<HTMLDivElement>) => {        
        e.stopPropagation();
        setDragging(true);
        
        if (containerRef.current) {
            setClickPoint(e.pageX);
            setScrollLeft(containerRef.current.scrollLeft);
        }
    };

    const handleMouseMoveEvent = (e: MouseEvent<HTMLDivElement>) => {
        e.stopPropagation();
        e.preventDefault();

        if (!dragging) return;
        if (containerRef.current) {
            const walk = e.pageX - clickPoint;

            containerRef.current.scrollLeft = scrollLeft - walk;
        }
    };

    return (
        <nav className={styles.nav} data-slot={"nav"}>
            <div
                className={styles.slider}
                onMouseDown={handleMouseDownEvent}
                onMouseLeave={() => setDragging(false)}
                onMouseUp={() => setDragging(false)}
                onMouseMove={handleMouseMoveEvent}
                ref={containerRef}
            >
                <Link href={"/map/all"}><TextMediumShared>전체</TextMediumShared></Link>
                <Link href={"/map/near"}><TextMediumShared>근처</TextMediumShared></Link>
                {
                    cityList.map(city => (
                        <React.Fragment key={city.cityId}>
                            <Link href={`/map/region?city=${city.cityName}`}><TextMediumShared>{city.cityName}</TextMediumShared></Link>
                        </React.Fragment>
                    ))
                }
            </div>
        </nav>
    )
}

export default PharmacyRegionBar;