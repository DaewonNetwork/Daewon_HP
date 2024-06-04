"use client";

import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
import { useRouter } from "next/navigation";
import React, { useEffect, useState } from "react";
import { MapMarker, Map } from "react-kakao-maps-sdk";

const PharmacyMap = ({ pharmacyList }: { pharmacyList: PharmacyType[] }) => {
    const { kakao } = window;
    const [map, setMap] = useState<any>();

    const router = useRouter();

    useEffect(() => {
        if(!kakao?.maps) return;
        if(!kakao?.maps.LatLngBounds) return;
        const bounds = new kakao.maps.LatLngBounds();

        pharmacyList.forEach((pharmacy) => {
            bounds.extend(new kakao.maps.LatLng(pharmacy.phY, pharmacy.phX));
        });
        
        if(!map) return;        
        map.setBounds(bounds);
        map.setLevel(5);
    }, [map, pharmacyList]);

    if(!kakao?.maps) return <></>;

    return (
        <Map level={5} onCreate={setMap} style={{ width: "100%", height: "100%" }} center={{ lat: 0, lng: 0}}>
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
        </Map>
    )
}

export default PharmacyMap;