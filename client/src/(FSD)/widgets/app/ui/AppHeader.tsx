import React from "react";
import { Barlow_Condensed } from "next/font/google";
import styles from "@/(FSD)/shareds/styles/AppStyle.module.scss";
import { Button } from "@nextui-org/button";
import Link from "next/link";

const logo = Barlow_Condensed({
    weight: "500",
    subsets: ["latin"],
    display: "swap",
});

const AppHeader = () => {
    return (
        <header className={`bg-background ${styles.header}`}>
            <div className={styles.inner}>
                <div className={styles.logo_box}>
                    <h1 className={"text-logo"}>
                        <Link href={"/"}>
                            <span className={`${logo.className}`}>HP</span><span>병원 평점</span>
                        </Link>
                    </h1>
                </div>
                <div className={styles.btn_box}>
                    <Button as={Link} href={"/auth/signin"}>로그인</Button>
                    <Button color={"primary"} as={Link} href={"/auth/signup"}>회원가입</Button>
                </div>
            </div>
        </header>
    );
};

export default AppHeader;