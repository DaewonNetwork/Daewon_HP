"use client";

import React, { useEffect } from "react";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";
import { notFound, useSearchParams } from "next/navigation";
import PharmacyMapShared from "@/(FSD)/shareds/ui/PharmacyMapShared";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
import { usePharmacyRegionKeywordMap } from "@/(FSD)/entities/pharmacy/api/usePharmacyRegionKeywordMap";

const PharmacyRegionKeywordMap = () => {
    const searchParams = useSearchParams();
    const city = searchParams.get("city")!;
    const keyword = searchParams.get("keyword")!;

    const { data, isError, isPending, refetch } = usePharmacyRegionKeywordMap(city, keyword);

    const pharmacyList: PharmacyType[] = data;

    useEffect(() => {
        refetch();
    }, [city, keyword]);

    if(isError && (pharmacyList && (!pharmacyList[0]))) return notFound();

    return (
        <div className={styles.map}>
            <PharmacyMapShared pharmacyList={pharmacyList} isPending={isPending} />
        </div>
    );
};

export default PharmacyRegionKeywordMap;