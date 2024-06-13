"use client";

import PharmacyNearList from "@/(FSD)/entities/pharmacy/ui/PharmacyNearList";
import PharmacyNearMap from "@/(FSD)/entities/pharmacy/ui/PharmacyNearMap";
import FooterShared from "@/(FSD)/shareds/ui/FooterShared";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import React, { useEffect, useState } from "react";
import AppFooter from "../../app/ui/AppFooter";

const PharmacyNear = () => {
    const [lat, setLat] = useState<number>(0);
    const [lng, setLng] = useState<number>(0);
    const [isGeoError, setIsGeoError] = useState<boolean>(false);
    const [isGeoPending, setIsGeoPending] = useState<boolean>(true);

    useEffect(() => {
        if ("geolocation" in navigator) {
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    setLat(position.coords.latitude);
                    setLng(position.coords.longitude);
                    setIsGeoPending(false);
                }, 
                (error) => {
                    setIsGeoError(true);
                    setIsGeoPending(false);
                }
            );
        } else {
            setIsGeoError(true);
            setIsGeoPending(false);
        }
    }, []);

    useEffect(() => {}, [lat, lng]);

    return (
        <>
            <PharmacyNearMap lat={lat} lng={lng} isGeoPending={isGeoPending} />
            <FooterShared>
                <ModalShared>
                    <PharmacyNearList lat={lat} lng={lng} isGeoPending={isGeoPending} />
                </ModalShared>
                <AppFooter />
            </FooterShared>
        </>
    );
};

export default PharmacyNear;