import PharmacyNearList from "@/(FSD)/entities/pharmacy/ui/PharmacyNearList";
import React from "react";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import PharmacyNearMap from "@/(FSD)/entities/pharmacy/ui/PharmacyNearMap";
import { Metadata } from "next";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import PharmacyRegionBar from "@/(FSD)/features/pharmacy/ui/PharmacyRegionBar";
import FooterShared from "@/(FSD)/shareds/ui/FooterShared";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";

export const metadata: Metadata = {
    title: "HP - 근처 지역 검색",
}

const Page = () => {
    return (
        <>
            <HeaderShared>
                <AppHeader>
                    <PharmacyRegionBar />
                </AppHeader>
            </HeaderShared>
            <PharmacyNearMap />
            <FooterShared>
                <ModalShared>
                    <PharmacyNearList />
                </ModalShared>
                <AppFooter />
            </FooterShared>
        </>
    );
};

export default Page;