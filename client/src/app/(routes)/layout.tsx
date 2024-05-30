import React from "react";
import RootProvider from "@/(FSD)/apps/providers/RootProvider";
import "@/(FSD)/shareds/styles/globalStyle.scss";

import type { Metadata } from "next";
import Head from "next/head";

export const metadata: Metadata = {
    title: "HP - 행복한 약국 평점 사이트",
}

const RootLayout = ({ children, }: { children: React.ReactNode }) => {
    return (
        <html lang="ko" suppressHydrationWarning={true}>
            <Head>
                <meta charSet="utf-8" />
            </Head>
            <body>
                <RootProvider>
                    <div className={"root"} suppressHydrationWarning={true}>
                        {children}
                    </div>
                </RootProvider>
            </body>
        </html>
    );
};

export default RootLayout;