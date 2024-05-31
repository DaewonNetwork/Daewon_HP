import RegionKeywordPharmacyList from "@/(FSD)/widgets/pharmacy/ui/RegionKeywordPharmacyList";
import React from "react";
import MapSearchRegionKeywordForm from "@/(FSD)/features/pharmacy/ui/MapSearchRegionKeywordForm";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader"
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import RegionKeywordMap from "@/(FSD)/widgets/map/ui/RegionKeywordMap";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import { Metadata } from "next";

export const metadata: Metadata = {
    title: "HP - 지역 내 검색",
}

const Page = () => {
    return (
        <>
            <AppHeader>
                <MapSearchRegionKeywordForm />
            </AppHeader>
            <RegionKeywordMap />
            <footer className={"footer"}>
                <ModalShared>
                    <RegionKeywordPharmacyList />
                </ModalShared>
                <AppFooter />
            </footer>
        </>
    )
}

export default Page;