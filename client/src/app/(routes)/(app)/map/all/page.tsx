import React from "react";
import AllPharmacyList from "@/(FSD)/widgets/pharmacy/ui/AllPharmacyList";
import MapSearchForm from "@/(FSD)/features/pharmacy/ui/MapSearchForm";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import AllMap from "@/(FSD)/widgets/map/ui/AllMap";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import { Metadata } from "next";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";

export const metadata: Metadata = {
    title: "HP - 전체 검색",
}

const Page = () => {
    return (
        <>
            <AppHeader>
                <MapSearchForm />
            </AppHeader>
            <AllMap />
            <footer className={"footer"}>
                <ModalShared>
                    <AllPharmacyList />
                </ModalShared>
                <AppFooter />
            </footer>
        </>
    );
};

export default Page;