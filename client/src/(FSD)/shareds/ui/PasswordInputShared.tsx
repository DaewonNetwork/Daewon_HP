"use client";

import React, { useLayoutEffect, useState } from "react";
import { Controller } from "react-hook-form";
import { Input } from "@nextui-org/input";
import IconShared from "./IconShared";

const PasswordInputShared = ({ name, control, onChange, children, ...props }: any) => {
    const [isVisible, setIsVisible] = useState(true);
    const [placeholder, setPlaceholder] = useState("");
    const toggleVisibility = () => setIsVisible(!isVisible);

    useLayoutEffect(() => {
        switch (name) {
            case "password":
                setPlaceholder("비밀번호");
                break;
            case "confirmPassword":
                setPlaceholder("비밀번호 재입력");
                break;
            default:
                break;
        }
    }, [name]);

    return (
        <Controller name={name} control={control} render={({ field }) => {
            const { onChange, onBlur, name, value } = field;

            return (
                <Input {...props}
                    {...props}
                    name={name}
                    value={value}

                    event={(e: any) => {
                        onChange(e);
                    }}
                    onBlur={(_: any) => {
                        onBlur();
                    }}

                    type={isVisible ? "password" : "text"}
                    size={"lg"} variant={"flat"}
                    isRequired
                    radius={"md"}
                    placeholder={children ? `${children}` : placeholder}

                    endContent={<>
                        <button className="focus:outline-none" type="button" onClick={toggleVisibility}>
                            <IconShared></IconShared>
                        </button>
                    </>}
                />
            )
        }}
        />
    );
};

PasswordInputShared.displayName = "PasswordInput";
export default PasswordInputShared;