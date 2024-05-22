import React from "react";
import styles from "@/(FSD)/shareds/styles/AppStyle.module.scss";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";

const Layout = ({ children }: { children: React.ReactNode }) => {
    return (
        <div className={`${styles.container}`}>
            <AppHeader />
            { children }
        </div>
    );
};

export default Layout;