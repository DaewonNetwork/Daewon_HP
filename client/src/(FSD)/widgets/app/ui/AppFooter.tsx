import LinkBtnShared from "@/(FSD)/shareds/ui/LinkBtnShared";
import React from "react";
import styles from "@/(FSD)/shareds/styles/AppStyle.module.scss";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import ContainerShared from "@/(FSD)/shareds/ui/ContainerShared";

const AppFooter = () => {
    return (
        <div className={styles.footer}>
            <ContainerShared>
                <InnerShared>
                    <LinkBtnShared href={"/review"}>리뷰</LinkBtnShared>
                    <LinkBtnShared href={"/"}>홈</LinkBtnShared>
                    <LinkBtnShared href={"/profile"}>프로필</LinkBtnShared>
                </InnerShared>
            </ContainerShared>
        </div>
    )
}

export default AppFooter;