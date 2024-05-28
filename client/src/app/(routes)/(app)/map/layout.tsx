import Script from "next/script";
import React from "react";

const Layout = ({ children }: { children: React.ReactNode }) => {
    
    return (
        <>
            {children}
        </>
    )
}

export default Layout;