"use client";

import React from "react";
import styles from "@/(FSD)/shareds/styles/AuthStyle.module.scss";
import Link from "next/link";
import { usePathname } from "next/navigation";
import { authHeaderStyle } from "../lib/authCustomStyle";

const AuthHeader = () => {
    const path = usePathname();

    const headerClassNames = authHeaderStyle(path === "/auth/signin" ? "signin" : "signup");

    return (
        <header className={`${styles.header} ${headerClassNames}`}>
            <h1 className={"text-lg"}>{ path === "/auth/signin" ? "로그인" : "회원가입" }</h1>
            <nav className={styles.nav}>
                <Link className={"text-md"} href={"/auth/signin"}>로그인하기</Link>
                <Link className={"text-md"} href={"/auth/signup"}>가입하기</Link>
            </nav>
        </header>
    );
};

export default AuthHeader;