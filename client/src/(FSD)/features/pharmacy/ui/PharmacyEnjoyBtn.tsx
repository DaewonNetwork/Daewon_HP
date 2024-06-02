"use client";

import IconShared from "@/(FSD)/shareds/ui/IconShared";
import TextXlargeShared from "@/(FSD)/shareds/ui/TextXlargeShared";
import { Button } from "@nextui-org/button";
import React, { useState } from "react";
import { usePharmacyEnjoyToggle } from "../api/usePharmacyEnjoyToggle";

const PharmacyEnjoyBtn = ({ phId, children, defaultLikeActive = false, set }: { defaultLikeActive?: boolean; phId: number; children?: React.ReactNode; set?: any }) => {
    const [isLikeActive, setIsLikeActive] = useState<boolean>(defaultLikeActive);
    
    const onSuccess = (data: any) => {
        if(set) {
            set(data);
        }
    };

    const { mutate } = usePharmacyEnjoyToggle({ onSuccess });
    return (
        <Button onClick={_ => mutate(Number(phId))} isIconOnly size={"sm"} variant={"light"} color={isLikeActive ? "primary" : "default"} endContent={<TextXlargeShared><IconShared iconType={"like"} />{children}</TextXlargeShared>} />
    )
}

export default PharmacyEnjoyBtn;