"use client";

import LinkBtnShared from "@/(FSD)/shareds/ui/LinkBtnShared";
import React from "react";
import styles from "@/(FSD)/shareds/styles/AppStyle.module.scss";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import ContainerShared from "@/(FSD)/shareds/ui/ContainerShared";
import IconShared from "@/(FSD)/shareds/ui/IconShared";
import { useSelectedLayoutSegment, useSelectedLayoutSegments } from "next/navigation";

const AppFooter = () => {
    const segment = useSelectedLayoutSegments();

    console.log(segment);
    

    return (
        <div className={styles.footer}>
            <ContainerShared>
                <InnerShared>
                    <LinkBtnShared href={"/review"} endContent={<IconShared iconType={"review"} />}>리뷰</LinkBtnShared>
                    <LinkBtnShared href={"/"} endContent={<IconShared iconType={"hospital"} />}>약국</LinkBtnShared>
                    <LinkBtnShared href={"/profile"} endContent={<IconShared iconType={"profile"} />}>프로필</LinkBtnShared>
                </InnerShared>
            </ContainerShared>
        </div>
    )
}

export default AppFooter;