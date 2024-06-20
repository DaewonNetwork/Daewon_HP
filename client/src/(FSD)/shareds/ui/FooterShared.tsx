import React from "react";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";

const FooterShared = ({ children }: { children: React.ReactNode }) => {
    return (
        <footer data-slot={"footer"} className={styles.footer}>
            {children}
        </footer>
    );
};

export default FooterShared;