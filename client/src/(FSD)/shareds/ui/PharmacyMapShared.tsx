"use client";

import React, { useEffect, useState } from "react";
import { MapMarker } from "react-kakao-maps-sdk";
import type { PharmacyMapType } from "../types/PharmacyMap.type";
import MapShared from "./MapShared";
import Loading from "@/(FSD)/widgets/app/ui/Loading";
import PharmacyInfoModal from "@/(FSD)/widgets/pharmacy/ui/PharmacyInfoModal";

const PharmacyMapShared = ({ pharmacyList, isPending }: PharmacyMapType) => {
    const { kakao } = window;
    const [map, setMap] = useState<kakao.maps.Map>();
    const [phId, setPhId] = useState<number>(0);
    const [isOpen, setIsOpen] = useState<boolean>(true);

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

    if (!kakao?.maps) return;
    if (isPending) return <Loading />;

    return (
        <>
            <MapShared onCreate={setMap} onClick={() => setIsOpen(false)}>
                {
                    pharmacyList.map(pharmacy => {
                        return (
                            <React.Fragment key={pharmacy.phId}>
                                <MapMarker
                                    onClick={_ => {
                                        setIsOpen(true);
                                        setPhId(pharmacy.phId);
                                        map?.panTo(new kakao.maps.LatLng(pharmacy.phY, pharmacy.phX));
                                    }}
                                    title={`${pharmacy.phName}`}
                                    position={{ lat: pharmacy.phY, lng: pharmacy.phX }} />
                            </React.Fragment>
                        )
                    })
                }
            </MapShared>
            {phId && <PharmacyInfoModal phId={phId} isOpen={isOpen} setIsOpen={setIsOpen} />}
        </>
    )
}

export default PharmacyMapShared;