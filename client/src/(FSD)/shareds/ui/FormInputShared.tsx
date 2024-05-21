"use client";

import { Controller } from "react-hook-form";
import { Input } from "@nextui-org/input";
import IconShared from "./IconShared";
import { FormInputType } from "../types/FormInput.type";

const FormInputShared = ({ name, control, ...props }: FormInputType) => {
    
    return (
        <Controller
            name={name}
            control={control}
            render={({ field }) => {
                const { onChange, onBlur, name, value } = field;

                return (
                    <Input
                        {...props}
                        name={name}
                        value={value}
                        isRequired
                        radius={"none"}
                        isClearable

                        onChange={(e : any) => {
                            onChange(e);

                            if (props.onChange) {
                                props.onChange(e);
                            }
                        }}
                        onBlur={(_ : any) => {
                            onBlur();
                        }}

                        endContent={
                            String(value).length >= 1 && (
                                <button
                                    className={"focus:outline-none"}
                                    type={"button"}
                                    onClick={() => {
                                        onChange("");
                                    }}
                                >
                                    <IconShared></IconShared>
                                </button>
                            )
                        }
                    />
                )
            }}
        />
    );
};

export default FormInputShared;