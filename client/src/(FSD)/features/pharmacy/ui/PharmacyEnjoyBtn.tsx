import IconShared from "@/(FSD)/shareds/ui/IconShared";
import TextXlargeShared from "@/(FSD)/shareds/ui/TextXlargeShared";
import { Button } from "@nextui-org/button";
import React from "react";

const PharmacyEnjoyBtn = ({ phId }: { phId: number }) => {
    return (
        <Button isIconOnly size={"sm"} variant={"light"} endContent={<TextXlargeShared><IconShared iconType={"like"} /></TextXlargeShared>} />
    )
}

export default PharmacyEnjoyBtn;