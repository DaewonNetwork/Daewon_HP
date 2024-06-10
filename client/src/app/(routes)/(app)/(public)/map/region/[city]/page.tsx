import PharmacyRegionKeywordForm from "@/(FSD)/features/map/ui/PharmacyPharmacyRegionKeywordForm";
import RegionPharmacyList from "@/(FSD)/widgets/pharmacy/ui/RegionPharmacyList";
import React from "react";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import RegionMap from "@/(FSD)/widgets/map/ui/RegionMap";
import { Metadata } from "next";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import PharmacyRegionBar from "@/(FSD)/features/map/ui/PharmacyPharmacyRegionBar";
import FooterShared from "@/(FSD)/shareds/ui/FooterShared";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";

export const metadata: Metadata = {
    title: "HP - 지역 검색",
}

const Page = () => {
    return (
        <>
            <HeaderShared>
                <AppHeader>
                    <PharmacyRegionBar />
                    <PharmacyRegionKeywordForm />
                </AppHeader>
            </HeaderShared>
            <RegionMap />
            <FooterShared>
                <ModalShared>
                    <RegionPharmacyList />
                </ModalShared>
                <AppFooter />
            </FooterShared>
        </>
    );
};

export default Page;