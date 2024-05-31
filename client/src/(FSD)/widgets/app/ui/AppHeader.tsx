"use client";

import React, { useEffect } from "react";
import { Barlow_Condensed } from "next/font/google";
import styles from "@/(FSD)/shareds/styles/AppStyle.module.scss";
import { Button } from "@nextui-org/button";
import Link from "next/link";
import MapSearchRegion from "@/(FSD)/features/pharmacy/ui/MapSearchRegion";
import useUserStore from "@/(FSD)/shareds/stores/useUserStore";

const logo = Barlow_Condensed({
    weight: "500",
    subsets: ["latin"],
    display: "swap",
});

const AppHeader = ({ children, isSearchRegion = true }: { children?: React.ReactNode, isSearchRegion?: boolean }) => {
    const { user } = useUserStore();
    
    
    useEffect(() => {
    }, [user]);

    return (
        <header className={`bg-background ${styles.header}`}>
            <div className={`${styles.header_inner}`}>
                <div>
                    <h1 className={"text-logo"}>
                        <Link className={logo.className} href={"/"}>HP</Link>
                    </h1>
                </div>
                <div>
                    {user?.userName && <Button variant={"light"} size={"md"} as={Link} href={"/profile"} className={"text-medium font-medium"}>{user.userName}님</Button>}
                    {!(user?.userName) && <Button variant={"light"} size={"md"} as={Link} href={"/auth/signin"}>로그인 / 가입하기</Button>}
                </div>
            </div>
            {isSearchRegion && <MapSearchRegion />}
            {children}
        </header>
    );
};

export default AppHeader;