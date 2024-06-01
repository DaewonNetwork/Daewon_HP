import PharmacyInfo from "@/(FSD)/entities/pharmacy/ui/PharmacyInfo";
import ReviewContaner from "@/(FSD)/entities/review/ui/ReviewContaner";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import React from "react";

const Page = () => {
    return (
        <>
            <HeaderShared>
                <AppHeader />
            </HeaderShared>
            <SectionShared>
                <PharmacyInfo />
                <ReviewContaner />
            </SectionShared>
        </>
    );
};

export default Page;