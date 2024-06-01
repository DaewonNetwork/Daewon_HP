import RegionKeywordPharmacyList from "@/(FSD)/widgets/pharmacy/ui/RegionKeywordPharmacyList";
import React from "react";
import MapSearchRegionKeywordForm from "@/(FSD)/features/pharmacy/ui/MapSearchRegionKeywordForm";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import RegionKeywordMap from "@/(FSD)/widgets/map/ui/RegionKeywordMap";
import { Metadata } from "next";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import MapSearchRegion from "@/(FSD)/features/pharmacy/ui/MapSearchRegion";
import FooterShared from "@/(FSD)/shareds/ui/FooterShared";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";

export const metadata: Metadata = {
    title: "HP - 지역 내 검색",
}

const Page = () => {
    return (
        <>
            <HeaderShared>
                <AppHeader>
                    <MapSearchRegion />
                    <MapSearchRegionKeywordForm />
                </AppHeader>
            </HeaderShared>
            <RegionKeywordMap />
            <FooterShared>
                <ModalShared>
                    <RegionKeywordPharmacyList />
                </ModalShared>
                <AppFooter />
            </FooterShared>
        </>
    )
}

export default Page;