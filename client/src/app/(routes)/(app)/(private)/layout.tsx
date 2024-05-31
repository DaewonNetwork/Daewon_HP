"use client";

import useIsLoggedIn from "@/(FSD)/shareds/hooks/useIsLoggedIn";
import React from "react";

const Layout = ({ children, }: { children: React.ReactNode }) => {
    useIsLoggedIn(true);

    return (
        <>
            {children}
        </>
    )
}

export default Layout;