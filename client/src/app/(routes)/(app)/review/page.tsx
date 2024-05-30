import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import React from "react";

const Page = () => {
    return (
        <>
            <AppHeader isSearchRegion={false} />
            <footer className={"footer"}>
                <AppFooter />
            </footer>
        </>
    );
};

export default Page;