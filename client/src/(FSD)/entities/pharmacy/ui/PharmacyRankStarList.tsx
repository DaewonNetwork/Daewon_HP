"use client";

import React from "react";
import TextLargeShared from "@/(FSD)/shareds/ui/TextLargeShared";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import { useReadRankStarPharmacys } from "../api/useReadRankStarPharmacys";

const PharmacyRankStarList = () => {
    const { data } = useReadRankStarPharmacys();

    console.log(data);
    
    return (
        <div>
            <InnerShared>
                <TextLargeShared>실시간 평점 순위</TextLargeShared>
            </InnerShared>
        </div>
    )
}

export default PharmacyRankStarList;