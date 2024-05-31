"use client";

import React from "react";
import Link from "next/link";
import { usePathname } from "next/navigation";
import { authHeaderStyle } from "../lib/authCustomStyle";

const AuthHeader = () => {
    const path = usePathname();

    const headerClassNames = authHeaderStyle(path === "/auth/signin" ? "signin" : "signup");

    return (
        <>
        
        </>
    );
};

export default AuthHeader;