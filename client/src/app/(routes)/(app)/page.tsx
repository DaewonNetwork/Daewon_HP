import React from "react";
import MapSearchForm from "@/(FSD)/features/pharmacy/ui/MapSearchForm";
import PhRankEnjoy from "@/(FSD)/entities/pharmacy/ui/PhRankEnjoy";
import PhRankStar from "@/(FSD)/entities/pharmacy/ui/PhRankStar";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import MapSearchRegion from "@/(FSD)/features/pharmacy/ui/MapSearchRegion";

const Page = () => {
    return (
        <>
            <HeaderShared>
                <AppHeader>
                    <MapSearchForm />
                    <MapSearchRegion />
                </AppHeader>
            </HeaderShared>
            <SectionShared>
                <PhRankEnjoy />
                <PhRankStar />
            </SectionShared>
        </>
    )
}

export default Page;