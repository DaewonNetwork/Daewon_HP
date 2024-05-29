"use client";

import React, { useEffect, useState } from "react";
import { useInView } from "react-intersection-observer";
import styles from "@/(FSD)/shareds/styles/PharmacyStyle.module.scss";
import PharmacyList from "@/(FSD)/entities/pharmacy/ui/PharmacyList";
import { useSearchNear } from "@/(FSD)/features/pharmacy/api/search/useSearchNear";

const NearPharmacyList = () => {
    const [lat, setLat] = useState<number>(0);
    const [lng, setLng] = useState<number>(0);

    const [isError, setIsError] = useState<boolean>(false);

    useEffect(() => {
        if ("geolocation" in navigator) {
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    setLat(position.coords.latitude);
                    setLng(position.coords.longitude);
                }
            )
        } else {
            setIsError(true);
        }
    }, [lat, lng]);

    const { pharmacyList, fetchNextPage } = useSearchNear(lat, lng);


    const { ref, inView } = useInView();

    useEffect(() => {
        if (inView) {
            fetchNextPage();
        }
    }, [inView]);
    

    if(isError || (!pharmacyList) || (!pharmacyList[0])) return <></>;

    return (
        <div className={styles.container}>
            <PharmacyList pharmacyList={pharmacyList} />
            <div ref={ref} />
        </div>
    );
};

export default NearPharmacyList;