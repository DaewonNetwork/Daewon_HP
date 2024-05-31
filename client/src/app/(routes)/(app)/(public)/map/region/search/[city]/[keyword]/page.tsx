import RegionKeywordPharmacyList from "@/(FSD)/widgets/pharmacy/ui/RegionKeywordPharmacyList";
import React from "react";
import MapSearchRegionKeywordForm from "@/(FSD)/features/pharmacy/ui/MapSearchRegionKeywordForm";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import RegionKeywordMap from "@/(FSD)/widgets/map/ui/RegionKeywordMap";
import { Metadata } from "next";

export const metadata: Metadata = {
    title: "HP - 지역 내 검색",
}

const Page = () => {
    return (
        <>
            <MapSearchRegionKeywordForm />
            <RegionKeywordMap />
            <ModalShared>
                <RegionKeywordPharmacyList />
            </ModalShared>
        </>
    )
}

export default Page;