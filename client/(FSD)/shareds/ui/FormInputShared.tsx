"use client";

import Shared from "@/(FSD)/shareds";
import { Controller } from "react-hook-form";
import type { FormInputType } from "../types/FormInputProps.type";

const FormInputShared = ({ name, control, placeholder, size, type = "text", isClearable, ...props }: FormInputType) => {
    return (
        <Controller
            name={name}
            control={control}
            render={({ field }) => {
                const { onChange, onBlur, name, value } = field;

                return (
                    <Shared.ui.Input
                        {...props}
                        name={name}
                        value={value}

                        event={(e : any) => {
                            onChange(e);
                            if (props.onChange) {
                                props.onChange(e);
                            }
                        }}
                        onBlur={(_ : any) => {
                            onBlur();
                        }}

                        type={type}
                        size={size || "lg"} variant={"flat"}
                        isRequired
                        radius={"md"}
                        placeholder={placeholder}

                        endContent={
                            isClearable &&
                            String(value).length >= 1 && (
                                <button
                                    className={"focus:outline-none"}
                                    type={"button"}
                                    onClick={() => {
                                        onChange("");
                                    }}
                                >
                                    <Shared.ui.Icon iconType={"close"} />   
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