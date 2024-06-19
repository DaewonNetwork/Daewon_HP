"use client";

import useAuthStatus from "@/(FSD)/shareds/hooks/useAuthStatus";
import Loading from "@/(FSD)/widgets/app/ui/Loading";
import React, { useEffect } from "react";

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