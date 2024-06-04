import React, { ReactNode } from "react";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";

const TextBoxShared = ({ children }: { children: ReactNode }) => {
    return (
        <div data-slot={"text_box"} className={styles.text_box}>
            {children}
        </div>
    )
}

export default TextBoxShared;