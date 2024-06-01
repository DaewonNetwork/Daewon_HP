import NearPharmacyList from "@/(FSD)/widgets/pharmacy/ui/NearPharmacyList";
import React from "react";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import NearMap from "@/(FSD)/widgets/map/ui/NearMap";
import { Metadata } from "next";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import MapSearchRegion from "@/(FSD)/features/pharmacy/ui/MapSearchRegion";
import FooterShared from "@/(FSD)/shareds/ui/FooterShared";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";

export const metadata: Metadata = {
    title: "HP - 근처 지역 검색",
}

const Page = () => {
    return (
        <>
            <HeaderShared>
                <AppHeader>
                    <MapSearchRegion />
                </AppHeader>
            </HeaderShared>
            <NearMap />
            <FooterShared>
                <ModalShared>
                    <NearPharmacyList />
                </ModalShared>
                <AppFooter />
            </FooterShared>
        </>
    );
};

export default Page;