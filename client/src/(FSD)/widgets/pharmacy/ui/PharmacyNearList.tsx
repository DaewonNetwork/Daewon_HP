"use client";

import React, { useEffect, useState } from "react";
import { useInView } from "react-intersection-observer";
import { usePharmacyNearSearch } from "@/(FSD)/entities/pharmacy/api/usePharmacyNearSearch";
import PharmacyShared from "@/(FSD)/shareds/ui/PharmacyShared";

const PharmacyNearList = () => {
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

    const { pharmacyList, fetchNextPage, refetch } = usePharmacyNearSearch(lat, lng);


    const { ref, inView } = useInView();

    useEffect(() => {
        if (inView) {
            fetchNextPage();
        }
    }, [inView]);


    if (isError || (!pharmacyList) || (!pharmacyList[0])) return <></>;

    return (
        <>
            {
                pharmacyList.map((pharmacy, index) => (
                    <React.Fragment key={index}>
                        <PharmacyShared pharmacy={pharmacy} parentRefetch={refetch} />
                    </React.Fragment>
                ))
            }
            <div ref={ref} />
        </>
    );
};

export default PharmacyNearList;