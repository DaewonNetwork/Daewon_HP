import React from "react";
import { CookiesProvider } from "next-client-cookies/server";

const CookieProvider = ({ children, }: Readonly<{ children: React.ReactNode; }>) => {
    return (
        <CookiesProvider>
            { children }
        </CookiesProvider>
    )
}

export default CookieProvider;