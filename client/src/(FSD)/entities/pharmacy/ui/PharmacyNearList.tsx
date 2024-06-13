"use client";

import React, { useEffect, useState } from "react";
import { useInView } from "react-intersection-observer";
import { usePharmacyNearSearch } from "@/(FSD)/entities/pharmacy/api/usePharmacyNearSearch";
import PharmacyShared from "@/(FSD)/shareds/ui/PharmacyShared";
import PharmacySkeletonShared from "@/(FSD)/shareds/ui/PharmacySkeletonShared";
import { notFound } from "next/navigation";
import Loading from "@/(FSD)/widgets/app/ui/Loading";

const PharmacyNearList = () => {
    const [lat, setLat] = useState<number>(0);
    const [lng, setLng] = useState<number>(0);

    const [isGeoError, setIsGeoError] = useState<boolean>(false);
    const [isGeoPending, setIsGeoPending] = useState<boolean>(true);

    const { pharmacyList, fetchNextPage, refetch, isFetchingNextPage, isPending, isError } = usePharmacyNearSearch(lat, lng);
    
    useEffect(() => {
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
            )
        } else {
            setIsGeoError(true);
            setIsGeoPending(false);
        }

        refetch();
    }, [pharmacyList, lat, lng]);

    const { ref, inView } = useInView();

    useEffect(() => {
        if (inView) {
            fetchNextPage();
        }
    }, [inView]);    

    if(isPending || isGeoPending) return <Loading />;
    if(isGeoError || isError) return notFound();

    return (
        <>
            {
                pharmacyList.map((pharmacy, index) => (
                    <React.Fragment key={index}>
                        <PharmacyShared pharmacy={pharmacy} parentRefetch={refetch} />
                    </React.Fragment>
                ))
            }
            {
                isFetchingNextPage ? <>
                    <PharmacySkeletonShared />
                    {
                        Array.from({ length: 9 }).map((_, index) => (
                            <React.Fragment key={index}>
                                <PharmacySkeletonShared />
                            </React.Fragment>
                        ))
                    }
                </> : <div ref={ref} />
            }
        </>
    );
};

export default PharmacyNearList;