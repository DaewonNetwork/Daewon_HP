"use client";

import React from "react";
import styles from "@/(FSD)/shareds/styles/AppStyle.module.scss";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import LogoShared from "@/(FSD)/shareds/ui/LogoShared";
import LinkBtnShared from "@/(FSD)/shareds/ui/LinkBtnShared";
import useUserStore from "@/(FSD)/shareds/stores/useUserStore";

const AppHeader = ({ children }: { children?: React.ReactNode }) => {
    const { isLoggedIn } = useUserStore();

    return (
        <div className={styles.header}>
            <InnerShared>
                <div className={styles.top_bar}>
                    <LogoShared />
                    {!isLoggedIn && <LinkBtnShared href={"/auth/signin"}>
                        로그인 / 가입하기
                    </LinkBtnShared>}
                </div>
                {children}
            </InnerShared>
        </div>
    )
}

export default AppHeader;