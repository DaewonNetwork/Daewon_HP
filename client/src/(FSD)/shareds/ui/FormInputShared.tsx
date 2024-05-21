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
                        isClearable
                        id={name}

                        onChange={(e: any) => {
                            onChange(e);

                            if (props.onChange) {
                                props.onChange(e);
                            }
                        }}
                        onBlur={(_: any) => {
                            onBlur();
                        }}

                        endContent={
                            <button className={"focus:outline-none"} type={"button"} onClick={() => { onChange(""); }}>
                                <IconShared iconType={"close"} />
                            </button>
                        }
                    />
                )
            }}
        />
    );
};

export default FormInputShared;