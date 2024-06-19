"use client";

import Loading from "@/(FSD)/widgets/app/ui/Loading";
import React, { useEffect } from "react";
import { useAuthStatus } from "@/(FSD)/shareds/hooks/useAuthStatus";

const Layout = ({ children, }: { children: React.ReactNode; }) => {

    const { isPending } = useAuthStatus();

    useEffect(() => {}, [isPending]);

    if(isPending) return <Loading />;

    return (
        <>
            {children}
        </>
    );
};

export default Layout;