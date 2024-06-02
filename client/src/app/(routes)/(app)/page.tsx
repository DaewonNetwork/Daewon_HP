import React from "react";
import MapSearchForm from "@/(FSD)/features/pharmacy/ui/MapSearchForm";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import MapSearchRegion from "@/(FSD)/features/pharmacy/ui/MapSearchRegion";
import FooterShared from "@/(FSD)/shareds/ui/FooterShared";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import PharmacyRankStarList from "@/(FSD)/entities/pharmacy/ui/PharmacyRankStarList";
import PharmacyRankEnjoyList from "@/(FSD)/entities/pharmacy/ui/PharmacyRankEnjoyList";

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
                <PharmacyRankEnjoyList />
                <PharmacyRankStarList />
            </SectionShared>
            <FooterShared>
                <AppFooter />
            </FooterShared>
        </>
    )
}

export default Page;