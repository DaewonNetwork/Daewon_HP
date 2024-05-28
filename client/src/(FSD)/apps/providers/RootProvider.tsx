import React from "react";
import QueryProvider from "./QueryProvider";
import UiProvider from "./UiProvider";
import MapProvider from "./MapProvider";

const RootProvider = ({ children }: Readonly<{ children: React.ReactNode; }>) => {
    return (
        <QueryProvider>
            <UiProvider>
                <MapProvider>
                    {children}
                </MapProvider>
            </UiProvider>
        </QueryProvider>
    );
};

export default RootProvider;