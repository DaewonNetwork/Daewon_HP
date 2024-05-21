import React from "react";
import "@/(FSD)/shareds/styles/globalStyle.scss";

import type { Metadata } from "next";

export const metadata: Metadata = {
    title: "HP - 로그인하기"
}

const Layout = ({ children, }: { children: React.ReactNode }) => {
    return (
        <>
            {children}
        </>
    );
};

export default Layout;