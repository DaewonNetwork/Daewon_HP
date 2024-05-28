import AuthSigninForm from "@/(FSD)/features/auth/ui/AuthSigninForm";
import { Metadata } from "next";
import React from "react";

export const metadata: Metadata = {
    title: "HP - 로그인하기"
}

const Page = () => {
    return (
        <AuthSigninForm />
    );
};

export default Page;