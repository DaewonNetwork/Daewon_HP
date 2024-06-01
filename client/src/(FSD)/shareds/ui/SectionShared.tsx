import React, { ReactNode } from "react";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";
import InnerShared from "./InnerShared";

const SectionShared = ({ children }: { children: ReactNode }) => {
    return (
        <div data-slot={"section"} className={`bg-background ${styles.section}`}>
            <InnerShared>
                {children}
            </InnerShared>
        </div>
    );
};

export default SectionShared;