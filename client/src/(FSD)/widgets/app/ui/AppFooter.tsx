"use client";

import React from "react";
import styles from "@/(FSD)/shareds/styles/AppStyle.module.scss";
import { Button } from "@nextui-org/button";
import Link from "next/link";
import IconShared from "@/(FSD)/shareds/ui/IconShared";

const AppFooter = () => {
    return (
        <div className={`bg-background ${styles.footer}`}>
            <Button className={"text-large"} variant={"light"} as={Link} href={"/review"} endContent={<IconShared className={"text-large"} iconType={"review"} />}>리뷰</Button>
            <Button className={"text-large"} variant={"light"} as={Link} href={"/"} endContent={<IconShared className={"text-large"} iconType={"map"} />}>지도</Button>
            <Button className={"text-large"} variant={"light"} as={Link} href={"/profile"} endContent={<IconShared className={"text-large"} iconType={"person"} />}>프로필</Button>
        </div>
    );
};

export default AppFooter;