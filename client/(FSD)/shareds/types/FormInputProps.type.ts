import type { InputType } from "@/(FSD)/shareds/types/InputProps.type";
import type { Control } from "react-hook-form";

export interface FormInputType extends InputType {
    control: Control;
    name: string;
    placeholder?: string;
    type?: string
};
