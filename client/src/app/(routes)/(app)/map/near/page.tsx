import NearPharmacyList from "@/(FSD)/widgets/pharmacy/ui/NearPharmacyList";
import React from "react";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import NearMap from "@/(FSD)/widgets/map/ui/NearMap";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import { Metadata } from "next";

export const metadata: Metadata = {
    title: "HP - 근처 지역 검색",
}

const Page = () => {
    return (
        <>
            <AppHeader />
            <NearMap />
            <footer className={"footer"}>
                <ModalShared>
                    <NearPharmacyList />
                </ModalShared>
                <AppFooter />
            </footer>
        </>
    );
};

export default Page;