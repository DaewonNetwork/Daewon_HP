import React from "react";
import { Barlow_Condensed } from "next/font/google";
import styles from "@/(FSD)/shareds/styles/AppStyle.module.scss";
import { Button } from "@nextui-org/button";
import Link from "next/link";
import IconShared from "@/(FSD)/shareds/ui/IconShared";

const logo = Barlow_Condensed({
    weight: "500",
    subsets: ["latin"],
    display: "swap",
});

const AppHeader = () => {
    return (
        <header className={`bg-background ${styles.header}`}>
            <div>
                <h1 className={"text-logo"}>
                    <Link className={logo.className} href={"/"}>HP</Link>
                </h1>
            </div>
            <div>
                <Button isIconOnly variant={"light"} endContent={<IconShared className={"text-logo"} iconType={"menu"} />}></Button>
            </div>
        </header>
    );
};

export default AppHeader;