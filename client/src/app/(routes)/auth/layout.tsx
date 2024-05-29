import React from "react";
import styles from "@/(FSD)/shareds/styles/AuthStyle.module.scss";
import AuthHeader from "@/(FSD)/widgets/auth/ui/AuthHeader";

const Layout = ({ children, }: { children: React.ReactNode }) => {
    return (
        <div className={styles.container}>
            <AuthHeader />
            {children}
        </div>
    );
};

export default Layout;