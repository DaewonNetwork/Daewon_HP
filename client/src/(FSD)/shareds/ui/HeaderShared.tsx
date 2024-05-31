import React from "react";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";
import ContainerShared from "./ContainerShared";

const HeaderShared = ({ children }: { children: React.ReactNode }) => {
    return (
        <header data-slot={"header"} className={styles.header}>
            <ContainerShared>
                {children}
            </ContainerShared>
        </header>
    );
};

export default HeaderShared;