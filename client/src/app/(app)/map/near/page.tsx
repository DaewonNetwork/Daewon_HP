import React from "react";
import { Metadata } from "next";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import PharmacyRegionBar from "@/(FSD)/features/pharmacy/ui/PharmacyRegionBar";
import PharmacyNearContainer from "@/(FSD)/widgets/pharmacy/ui/PharmacyNearContainer";

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
            <PharmacyNearContainer />
        </>
    );
};

export default Page;