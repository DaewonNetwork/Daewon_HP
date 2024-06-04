import React from "react";
import MapSearchForm from "@/(FSD)/features/pharmacy/ui/MapSearchForm";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import MapSearchRegion from "@/(FSD)/features/pharmacy/ui/MapSearchRegion";
import FooterShared from "@/(FSD)/shareds/ui/FooterShared";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import PharmacyRankContainer from "@/(FSD)/widgets/pharmacy/ui/PharmacyRankContainer";

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
                <PharmacyRankContainer />
            </SectionShared>
            <FooterShared>
                <AppFooter />
            </FooterShared>
        </>
    )
}

export default Page;