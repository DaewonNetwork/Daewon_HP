"use client";

import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";
import React from "react";
import FormInputShared from "@/(FSD)/shareds/ui/FormInputShared";

const SigninForm = () => {
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$/;

    const schema = z.object({
        password: z.string().regex(passwordRegex, {
            message: "알맞는 비밀번호를 입력해주세요."
        }),
        email: z.string().regex(emailRegex, {
            message: "알맞은 이메일 주소를 입력해주세요."
        })
    });

    const { control, handleSubmit, formState: { errors, isValid, submitCount } } = useForm({
        resolver: zodResolver(schema),
        mode: "onChange"
    });

    const onSubmit = (data: any) => {
        if ((!data.email) || (!data.password)) return;
    };

    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <FormInputShared variant={"underlined"} isInvalid={!!errors.email} errorMessage={errors.email && <>{errors.email.message}</>} name={"email"} control={control} placeholder={"이메일을 입력해주세요."} />
        </form>
    );
};

export default SigninForm;