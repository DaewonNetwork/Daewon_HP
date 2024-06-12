import PharmacyRegionKeywordList from "@/(FSD)/entities/pharmacy/ui/PharmacyRegionKeywordList";
import React from "react";
import PharmacyRegionKeywordForm from "@/(FSD)/features/pharmacy/ui/PharmacyRegionKeywordForm";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import PharmacyRegionKeywordMap from "@/(FSD)/entities/pharmacy/ui/PharmacyRegionKeywordMap";
import { Metadata } from "next";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import PharmacyRegionBar from "@/(FSD)/features/pharmacy/ui/PharmacyRegionBar";
import FooterShared from "@/(FSD)/shareds/ui/FooterShared";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";

export const metadata: Metadata = {
    title: "HP - 지역 내 검색",
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
            <SectionShared>
                <PharmacyRegionKeywordMap />
            </SectionShared>
            <FooterShared>
                <ModalShared>
                    <PharmacyRegionKeywordList />
                </ModalShared>
                <AppFooter />
            </FooterShared>
        </>
    )
}

export default Page;