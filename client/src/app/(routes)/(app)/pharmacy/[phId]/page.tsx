import PharmacyInfo from "@/(FSD)/entities/pharmacy/ui/PharmacyInfo";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import React from "react";

const Page = () => {
    return (
        <div>
            <div>
                <AppHeader isSearchRegion={false} />
            </div>
            <PharmacyInfo />
            <div className={"footer"}>
                <AppFooter />
            </div>
        </div>
    );
};

export default Page;