import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import PharmacyInfoContainer from "@/(FSD)/entities/pharmacy/ui/PharmacyInfoContainer";
import React from "react";

const Page = () => {
    return (
        <>
            <HeaderShared>
                <AppHeader />
            </HeaderShared>
            <SectionShared>
                <PharmacyInfoContainer />
            </SectionShared>
        </>
    );
};

export default Page;