import AuthSignupForm from "@/(FSD)/features/auth/ui/AuthSignupForm";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import LinkBtnShared from "@/(FSD)/shareds/ui/LinkBtnShared";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";
import TitleHeader from "@/(FSD)/widgets/app/ui/TitleHeader";
import { Metadata } from "next";
import React from "react";

export const metadata: Metadata = {
    title: "HP - 가입하기"
}

const Page = () => {

    return (
        <>
            <HeaderShared>
                <TitleHeader title={"가입하기"} />
            </HeaderShared>
            <SectionShared>
                <InnerShared>
                    <AuthSignupForm />
                    <LinkBtnShared href={"/auth/signin"}>로그인으로</LinkBtnShared>
                </InnerShared>
            </SectionShared>
        </>
    );
};

export default Page;