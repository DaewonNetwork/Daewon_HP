import React from "react";
import PharmacyKeywordForm from "@/(FSD)/features/map/ui/PharmacyPharmacyKeywordForm";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import PharmacyRegionBar from "@/(FSD)/features/map/ui/PharmacyPharmacyRegionBar";
import FooterShared from "@/(FSD)/shareds/ui/FooterShared";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import PharmacyRankContainer from "@/(FSD)/widgets/pharmacy/ui/PharmacyRankContainer";

const Page = () => {
    return (
        <>
            <HeaderShared>
                <AppHeader>
                    <PharmacyKeywordForm />
                    <PharmacyRegionBar />
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