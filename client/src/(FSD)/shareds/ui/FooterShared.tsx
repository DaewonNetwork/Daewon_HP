import React from "react";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";
import ContainerShared from "./ContainerShared";

const FooterShared = ({ children }: { children: React.ReactNode }) => {
    return (
        <footer data-slot={"footer"} className={styles.footer}>
            <ContainerShared>
                {children}
            </ContainerShared>
        </footer>
    );
};

export default FooterShared;