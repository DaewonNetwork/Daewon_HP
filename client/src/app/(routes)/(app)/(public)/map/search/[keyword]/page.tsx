import KeywordPharmacyList from "@/(FSD)/widgets/pharmacy/ui/KeywordPharmacyList";
import React from "react";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import KeywordMap from "@/(FSD)/widgets/map/ui/KeywordMap";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import { Metadata } from "next";
import MapSearchForm from "@/(FSD)/features/pharmacy/ui/MapSearchForm";

export const metadata: Metadata = {
    title: "HP - 키워드 검색",
}

const Page = () => {
    return (
        <>
            <AppHeader>
                <MapSearchForm />
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