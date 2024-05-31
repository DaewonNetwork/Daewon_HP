import MapSearchRegionKeywordForm from "@/(FSD)/features/pharmacy/ui/MapSearchRegionKeywordForm";
import RegionPharmacyList from "@/(FSD)/widgets/pharmacy/ui/RegionPharmacyList";
import React from "react";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import RegionMap from "@/(FSD)/widgets/map/ui/RegionMap";
import { Metadata } from "next";

export const metadata: Metadata = {
    title: "HP - 지역 검색",
}

const Page = () => {
    return (
        <>
            <MapSearchRegionKeywordForm />
            <RegionMap />
            <ModalShared>
                <RegionPharmacyList />
            </ModalShared>
        </>
    );
};

export default Page;