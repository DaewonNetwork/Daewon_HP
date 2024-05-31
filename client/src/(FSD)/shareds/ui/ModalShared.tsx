"use client";

import React, { useState } from "react";
import type { ModalType } from "../types/Modal.type";

const ModalShared = ({ children }: ModalType) => {
    const [isOpen, setOpen] = useState<boolean>();

    const openClick = () => setOpen(!isOpen);

    return (
        <>
        
        </>
    )
}

export default ModalShared;