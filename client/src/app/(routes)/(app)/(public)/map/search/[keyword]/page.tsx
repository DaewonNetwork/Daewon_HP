import KeywordPharmacyList from "@/(FSD)/widgets/pharmacy/ui/KeywordPharmacyList";
import React from "react";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import KeywordMap from "@/(FSD)/widgets/map/ui/KeywordMap";
import { Metadata } from "next";
import PharmacyKeywordForm from "@/(FSD)/features/map/ui/PharmacyPharmacyKeywordForm";
import FooterShared from "@/(FSD)/shareds/ui/FooterShared";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import PharmacyRegionBar from "@/(FSD)/features/map/ui/PharmacyPharmacyRegionBar";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";

export const metadata: Metadata = {
    title: "HP - 키워드 검색",
}

const Page = () => {
    return (
        <>
            <HeaderShared>
                <AppHeader>
                    <PharmacyRegionBar />
                    <PharmacyKeywordForm />
                </AppHeader>
            </HeaderShared>
            <KeywordMap />
            <FooterShared>
                <ModalShared>
                    <KeywordPharmacyList />
                </ModalShared>
                <AppFooter />
            </FooterShared>
        </>
    );
};

export default Page;