import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import React from "react";

const Page = () => {
    return (
        <div>
            <div>
                <AppHeader isSearchRegion={false} />
            </div>
            <div className={"footer"}>
                <AppFooter />
            </div>
        </div>
    );
};

export default Page;