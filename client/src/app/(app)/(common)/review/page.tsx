import FooterShared from "@/(FSD)/shareds/ui/FooterShared";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import TitleHeader from "@/(FSD)/widgets/app/ui/TitleHeader";
import React from "react";

const Page = () => {
    return (
        <>
            <HeaderShared>
                <TitleHeader title={"리뷰"} />
            </HeaderShared>
            <SectionShared>
                리뷰 모음
            </SectionShared>
            <FooterShared>
                <AppFooter />
            </FooterShared>
        </>
    );
};

export default Page;