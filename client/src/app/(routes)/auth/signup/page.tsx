import SignupForm from "@/(FSD)/features/auth/ui/SignupForm";
import { Metadata } from "next";
import React from "react";

export const metadata: Metadata = {
    title: "HP - 가입하기"
}

const Page = () => {

    return (
        <SignupForm />
    );
};

export default Page;