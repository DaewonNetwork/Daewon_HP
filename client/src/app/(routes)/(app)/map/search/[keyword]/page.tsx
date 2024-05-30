import KeywordPharmacyList from "@/(FSD)/widgets/pharmacy/ui/KeywordPharmacyList";
import React from "react";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import MapSearchRegionKeywordForm from "@/(FSD)/features/pharmacy/ui/MapSearchRegionKeywordForm";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import KeywordMap from "@/(FSD)/widgets/map/ui/KeywordMap";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import { Metadata } from "next";

export const metadata: Metadata = {
    title: "HP - 키워드 검색",
}

const Page = () => {
    return (
        <>
            <AppHeader>
                <MapSearchRegionKeywordForm />
            </AppHeader>
            <KeywordMap />
            <footer className={"footer"}>
                <ModalShared>
                    <KeywordPharmacyList />
                </ModalShared>
                <AppFooter />
            </footer>
        </>
    );
};

export default Page;