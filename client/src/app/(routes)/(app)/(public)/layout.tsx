"use client";

import useIsLoggedIn from "@/(FSD)/shareds/hooks/useIsLoggedIn";
import React from "react";

const Layout = ({ children, }: { children: React.ReactNode }) => {
    useIsLoggedIn(false);

    return (
        <>
            {children}
        </>
    )
}

export default Layout;