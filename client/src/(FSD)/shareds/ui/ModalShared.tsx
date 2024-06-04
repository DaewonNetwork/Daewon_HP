"use client";

import React, { useState } from "react";
import styles from "@/(FSD)/shareds/styles/AppStyle.module.scss";
import type { ModalType } from "../types/Modal.type";

const ModalShared = ({ children }: ModalType) => {
    const [isOpen, setOpen] = useState<boolean>();

    const openClick = () => setOpen(!isOpen);

    return (
        <>
            <div className={`bg-background ${styles.modal} ${isOpen ? styles.open : ""}`}>
                <div className={styles.modal_header} onClick={openClick}>
                    <div className={`bg-content4 ${styles.modal_btn}`}></div>
                </div>
                <div className={styles.modal_body}>
                    {children}
                </div>
            </div>
            {isOpen && <div className={styles.modal_bg} style={{ zIndex: isOpen ? 1 : -1 }} onClick={_ => isOpen && setOpen(false)}></div>}
        </>
    )
}

export default ModalShared;