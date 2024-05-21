import React from "react";
import RootProvider from "@/(FSD)/apps/providers/RootProvider";
import "@/(FSD)/shareds/styles/globalStyle.scss";

import Head from "next/head";

const RootLayout = ({ children, }: { children: React.ReactNode }) => {
    return (
        <html lang="ko" suppressHydrationWarning={true}>
            <Head>
                <title>HP - 행복한 약국 평점 사이트</title>
                <meta name="viewport" content="width=device-width, initial-scale=1.0" />
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