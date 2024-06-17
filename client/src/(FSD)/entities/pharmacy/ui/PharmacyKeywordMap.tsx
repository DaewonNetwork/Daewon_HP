"use client";

import React, { useEffect } from "react";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";
import { notFound, useSearchParams } from "next/navigation";
import { usePharmacyKeywordMap } from "@/(FSD)/entities/pharmacy/api/usePharmacyKeywordMap";
import PharmacyMapShared from "@/(FSD)/shareds/ui/PharmacyMapShared";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";

const PharmacyKeywordMap = () => {
    const searchParams = useSearchParams();
    const keyword = searchParams.get("keyword")!;

    const { data, isError, isPending, refetch } = usePharmacyKeywordMap(keyword);

    const pharmacyList: PharmacyType[] = data;
    

    useEffect(() => {
        refetch();
    }, [data, keyword]);

    if(isError && (pharmacyList && (!pharmacyList[0]))) return notFound();

    return (
        <div className={styles.map}>
            <PharmacyMapShared pharmacyList={pharmacyList} isPending={isPending} />
        </div>
    );
};

export default PharmacyKeywordMap;