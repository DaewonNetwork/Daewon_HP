import AuthSignupForm from "@/(FSD)/features/auth/ui/AuthAuthSignupForm";
import { Metadata } from "next";
import React from "react";

export const metadata: Metadata = {
    title: "HP - 가입하기"
}

const Page = () => {

    return (
        <AuthSignupForm />
    );
};

export default Page;