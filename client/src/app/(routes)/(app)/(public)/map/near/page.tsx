import NearPharmacyList from "@/(FSD)/widgets/pharmacy/ui/NearPharmacyList";
import React from "react";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import NearMap from "@/(FSD)/widgets/map/ui/NearMap";
import { Metadata } from "next";

export const metadata: Metadata = {
    title: "HP - 근처 지역 검색",
}

const Page = () => {
    return (
        <>
            <NearMap />
            <ModalShared>
                <NearPharmacyList />
            </ModalShared>
        </>
    );
};

export default Page;