"use client";

import useAuthStatus from "@/(FSD)/apps/hooks/useAuthStatus";
import React from "react";


const Layout = ({ children, }: { children: React.ReactNode }) => {
    useAuthStatus();

    return (
        <>
            {children}
        </>
    );
};

export default Layout;