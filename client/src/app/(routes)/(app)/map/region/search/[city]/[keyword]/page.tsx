import RegionKeywordPharmacyList from "@/(FSD)/widgets/pharmacy/ui/RegionKeywordPharmacyList";
import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import MapSearchRegionKeywordForm from "@/(FSD)/features/pharmacy/ui/MapSearchRegionKeywordForm";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import RegionKeywordMap from "@/(FSD)/widgets/map/ui/RegionKeywordMap";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import { Metadata } from "next";

export const metadata: Metadata = {
    title: "HP - 지역 내 검색",
}

const Page = () => {
    return (
        <>
            <AppHeader>
                <MapSearchRegionKeywordForm />
            </AppHeader>
            <RegionKeywordMap />
            <div className={"footer"}>
                <ModalShared>
                    <RegionKeywordPharmacyList />
                </ModalShared>
                <AppFooter />
            </div>
        </>
    )
}

export default Page;