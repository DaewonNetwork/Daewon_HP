import React from "react";
import styles from "@/(FSD)/shareds/styles/AuthStyle.module.scss";
import AuthHeader from "@/(FSD)/widgets/auth/ui/AuthHeader";

const Layout = ({ children, }: { children: React.ReactNode }) => {
    return (
        <div className={`bg-default-100 ${styles.container}`}>
            <div className={`bg-background ${styles.signin_box}`}>
                <AuthHeader />
                { children }
            </div>
        </div>
    );
};

export default Layout;