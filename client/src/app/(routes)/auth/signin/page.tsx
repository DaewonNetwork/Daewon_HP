import SigninForm from "@/(FSD)/features/auth/ui/SigninForm";
import { Metadata } from "next";
import React from "react";

export const metadata: Metadata = {
    title: "HP - 로그인하기"
}

const Page = () => {
    return (
        <SigninForm />
    );
};

export default Page;