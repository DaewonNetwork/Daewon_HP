import AuthSigninForm from "@/(FSD)/features/auth/ui/AuthSigninForm";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import LinkBtnShared from "@/(FSD)/shareds/ui/LinkBtnShared";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";
import TitleHeader from "@/(FSD)/widgets/app/ui/TitleHeader";
import { Metadata } from "next";
import React from "react";

export const metadata: Metadata = {
    title: "HP - 로그인하기"
}

const Page = () => {
    return (
        <>
            <HeaderShared>
                <TitleHeader title={"로그인"} />
            </HeaderShared>
            <SectionShared> 
                <InnerShared>
                    <AuthSigninForm />
                    <LinkBtnShared href={"/auth/signup"}>가입하기로</LinkBtnShared>
                </InnerShared>
            </SectionShared>
        </>
    );
};

export default Page;