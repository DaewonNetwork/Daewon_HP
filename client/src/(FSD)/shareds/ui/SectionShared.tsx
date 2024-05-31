import React, { ReactNode } from "react";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";

const SectionShared = ({ children }: { children: ReactNode }) => {
    return (
        <div data-slot={"section"} className={`bg-background ${styles.section}`}>
            { children }
        </div>
    );
};

export default SectionShared;