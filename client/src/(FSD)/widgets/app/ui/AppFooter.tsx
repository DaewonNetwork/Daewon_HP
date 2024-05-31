import LinkBtnShared from "@/(FSD)/shareds/ui/LinkBtnShared";
import React from "react";
import styles from "@/(FSD)/shareds/styles/AppStyle.module.scss";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";

const AppFooter = () => {
    return (
        <div className={styles.footer}>
            <InnerShared>
                <LinkBtnShared href={"/"}>홈</LinkBtnShared>
                <LinkBtnShared href={"/"}>홈</LinkBtnShared>
                <LinkBtnShared href={"/"}>홈</LinkBtnShared>
            </InnerShared>
        </div>
    )
}

export default AppFooter;