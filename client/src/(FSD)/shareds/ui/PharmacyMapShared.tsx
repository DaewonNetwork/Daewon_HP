"use client";

import { useRouter } from "next/navigation";
import React, { useEffect, useMemo, useState } from "react";
import { MapMarker, Map, useMap } from "react-kakao-maps-sdk";
import type { PharmacyMapType } from "../types/PharmacyMap.type";
import MapShared from "./MapShared";
import Loading from "@/(FSD)/widgets/app/ui/Loading";

const PharmacyMapShared = ({ pharmacyList, isPending }: PharmacyMapType) => {
    const { kakao } = window;
    const [map, setMap] = useState<kakao.maps.Map>();

    const router = useRouter();

    useEffect(() => {
        if (!kakao?.maps) return;
        if (!kakao?.maps.LatLngBounds) return;
        if (isPending) return;

        const bounds = new kakao.maps.LatLngBounds();

        pharmacyList.forEach((pharmacy) => {
            bounds.extend(new kakao.maps.LatLng(pharmacy.phY, pharmacy.phX));
        });

        if (!map) return;
        map.setBounds(bounds);
        map.setLevel(5);
    }, [map, isPending, pharmacyList]);

    if(!kakao?.maps) return;
    if ((isPending) || (!pharmacyList)) return <Loading />;
    if (pharmacyList.length === 0) return <Loading />;
    

    return (
        <MapShared onCreate={setMap}>
            {
                pharmacyList.map((pharmacy, index) => {
                    return (
                        <React.Fragment key={index}>
                            <MapMarker onClick={(e) => {
                                router.push(`/pharmacy/${e.getTitle()}`);
                            }} title={`${pharmacy.phId}`} position={{ lat: pharmacy.phY, lng: pharmacy.phX }} />
                        </React.Fragment>
                    )
                })
            }
        </MapShared>
    )
}

export default PharmacyMapShared;