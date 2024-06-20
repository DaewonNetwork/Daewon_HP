import React from "react";
import FooterShared from "@/(FSD)/shareds/ui/FooterShared";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import TitleHeader from "@/(FSD)/widgets/app/ui/TitleHeader";
import UserProfileContainer from "@/(FSD)/widgets/user/ui/UserProfileContainer";

const Page = () => {
    return (
        <>
            <HeaderShared>
                <TitleHeader title={"프로필"} />
            </HeaderShared>
            <SectionShared>
                <UserProfileContainer />
            </SectionShared>
            <FooterShared>
                <AppFooter />
            </FooterShared>
        </>
    );
};

export default Page;