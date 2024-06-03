"use client";

import useIsLoggedIn from "@/(FSD)/shareds/hooks/useIsLoggedIn";
import FooterShared from "@/(FSD)/shareds/ui/FooterShared";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import React from "react";

const Layout = ({ children, }: { children: React.ReactNode }) => {
    useIsLoggedIn(false);

    return (
        <>
            {children}
            <FooterShared>
                <AppFooter />
            </FooterShared>
        </>
    );
};

export default Layout;