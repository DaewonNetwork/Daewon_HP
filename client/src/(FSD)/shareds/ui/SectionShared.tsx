import React, { ReactNode } from "react";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";

const SectionShared = ({ children }: { children: ReactNode }) => {
    return (
        <section data-slot={"section"} className={`bg-background ${styles.section}`}>
            {children}
        </section>
    );
};

export default SectionShared;