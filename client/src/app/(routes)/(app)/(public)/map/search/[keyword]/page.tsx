import PharmacyKeywordList from "@/(FSD)/entities/pharmacy/ui/PharmacyKeywordList";
import React from "react";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import PharmacyKeywordMap from "@/(FSD)/entities/pharmacy/ui/PharmacyKeywordMap";
import { Metadata } from "next";
import PharmacyKeywordForm from "@/(FSD)/features/pharmacy/ui/PharmacyKeywordForm";
import FooterShared from "@/(FSD)/shareds/ui/FooterShared";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import PharmacyRegionBar from "@/(FSD)/features/pharmacy/ui/PharmacyRegionBar";
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
            <PharmacyKeywordMap />
            <FooterShared>
                <ModalShared>
                    <PharmacyKeywordList />
                </ModalShared>
                <AppFooter />
            </FooterShared>
        </>
    );
};

export default Page;