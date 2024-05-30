import React from "react";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";

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