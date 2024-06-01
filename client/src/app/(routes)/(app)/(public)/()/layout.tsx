import FooterShared from "@/(FSD)/shareds/ui/FooterShared";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import React from "react";

const Layout = ({ children, }: { children: React.ReactNode }) => {
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