import KeywordPharmacyList from "@/(FSD)/widgets/pharmacy/ui/KeywordPharmacyList";
import React from "react";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import KeywordMap from "@/(FSD)/widgets/map/ui/KeywordMap";
import { Metadata } from "next";
import MapSearchForm from "@/(FSD)/features/pharmacy/ui/MapSearchForm";

export const metadata: Metadata = {
    title: "HP - 키워드 검색",
}

const Page = () => {
    return (
        <>
            <MapSearchForm />
            <KeywordMap />
            <ModalShared>
                <KeywordPharmacyList />
            </ModalShared>
        </>
    );
};

export default Page;