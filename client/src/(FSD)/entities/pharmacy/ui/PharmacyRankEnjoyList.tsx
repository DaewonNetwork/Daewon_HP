"use client";

import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import React from "react";
import { useReadRankEnjoyPharmacys } from "../api/useReadRankEnjoyPharmacys";
import TextLargeShared from "@/(FSD)/shareds/ui/TextLargeShared";

const PharmacyRankEnjoyList = () => {
    const { data } = useReadRankEnjoyPharmacys();
    
    console.log(data);
    
    return (
        <div>
            <InnerShared>
                <TextLargeShared>사람들이 즐겨찾는 약국 TOP 10</TextLargeShared>
            </InnerShared>
        </div>
    )
}

export default PharmacyRankEnjoyList;