import React from "react";
import { Barlow_Condensed } from "next/font/google";
import Link from "next/link";

const logo = Barlow_Condensed({
    weight: "500",
    subsets: ["latin"],
    display: "swap",
});


const LogoShared = () => {
    return (
        <h1 data-slot={"text_logo"} className={"text-logo"}>
            <Link href={"/"}>
                <span className={logo.className}>HP</span>
            </Link>
        </h1>
    );
};

export default LogoShared;