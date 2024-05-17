import { Button } from "@nextui-org/button";
import React from "react";
import { ButtonType } from "../types/ButtonProps.type";

const ButtonShared = ({ children, ...props }: ButtonType) => {
    return (
        <Button {...props}>{ children }</Button>
    );
};

export default ButtonShared;