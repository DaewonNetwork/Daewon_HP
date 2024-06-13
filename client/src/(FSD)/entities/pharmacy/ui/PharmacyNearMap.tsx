    "use client";

    import React, { useCallback, useEffect, useState } from "react";
    import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";
    import PharmacyMapShared from "@/(FSD)/shareds/ui/PharmacyMapShared";
    import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
    import { usePharmacyNearMap } from "@/(FSD)/entities/pharmacy/api/usePharmacyNearMap";
    import { notFound } from "next/navigation";
    import Loading from "@/(FSD)/widgets/app/ui/Loading";

    const PharmacyNearMap = () => {
        const [lat, setLat] = useState<number>(0);
        const [lng, setLng] = useState<number>(0);

        const [isGeoError, setIsGeoError] = useState<boolean>(false);
        const [isGeoPending, setIsGeoPending] = useState<boolean>(true);
        const { data, isError, isPending, refetch } = usePharmacyNearMap(lat, lng);
        
        const pharmacyList: PharmacyType[] = data;
        
        if ("geolocation" in navigator) {
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    setLat(position.coords.latitude);
                    setLng(position.coords.longitude);
                    setIsGeoPending(false);
                }, (error) => {
                    setIsGeoError(true);
                    setIsGeoPending(false);
                }
            );
        } else {
            setIsGeoError(true);
            setIsGeoPending(false);
        }

        useEffect(() => {
            if (!lat && !lng) {
                refetch();
            }
        }, [lat, lng, pharmacyList]);

        if(isError) return notFound();

        return (
            <div className={styles.map}>
                <PharmacyMapShared pharmacyList={pharmacyList} isPending={isPending || isGeoPending} />
            </div>
        );
    };

    export default PharmacyNearMap;