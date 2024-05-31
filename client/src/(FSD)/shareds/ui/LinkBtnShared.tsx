import React from "react";
import { Button } from "@nextui-org/button";
import { LinkBtnType } from "../types/LinkBtn.type";
import Link from "next/link";

const LinkBtnShared = ({ href, children, ...props } : LinkBtnType) => {
    return (
        <Button data-slot={"link"} href={href} as={Link} {...props}>{ children }</Button>
    );
};

export default LinkBtnShared;