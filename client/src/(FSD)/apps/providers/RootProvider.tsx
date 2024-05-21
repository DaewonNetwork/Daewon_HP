import React from "react";
import QueryProvider from "./QueryProvider";
import UiProvider from "./UiProvider";
import CookieProvider from "./CookieProvider";

const RootProvider = ({ children }: Readonly<{ children: React.ReactNode; }>) => {
    return (
        <QueryProvider>
            <CookieProvider>
                <UiProvider>
                    {children}
                </UiProvider>
            </CookieProvider>
        </QueryProvider>
    );
};

export default RootProvider;