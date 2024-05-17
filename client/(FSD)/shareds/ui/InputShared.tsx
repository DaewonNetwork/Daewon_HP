import React from "react";
import { Input } from "@nextui-org/input";
import { InputType } from "../types/InputProps.type";

const InputShared = ({ ...props }: InputType) => {
    return (
        <Input {...props} />
    );
};

export default InputShared;