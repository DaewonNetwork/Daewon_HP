import MapSearchRegionKeywordForm from "@/(FSD)/features/pharmacy/ui/MapSearchRegionKeywordForm";
import RegionPharmacyList from "@/(FSD)/widgets/pharmacy/ui/RegionPharmacyList";
import React from "react";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import RegionMap from "@/(FSD)/widgets/map/ui/RegionMap";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import { Metadata } from "next";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";

export const metadata: Metadata = {
    title: "HP - 지역 검색",
}

const Page = () => {
    return (
        <>
            <AppHeader>
                <MapSearchRegionKeywordForm />
            </AppHeader>
            <RegionMap />
            <footer className={"footer"}>
                <ModalShared>
                    <RegionPharmacyList />
                </ModalShared>
                <AppFooter />
            </footer>
        </>
    );
};

export default Page;