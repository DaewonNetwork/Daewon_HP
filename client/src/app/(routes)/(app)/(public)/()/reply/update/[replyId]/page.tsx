import ReplyUpdateForm from "@/(FSD)/features/reply/ui/ReplyUpdateForm";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";
import TitleHeader from "@/(FSD)/widgets/app/ui/TitleHeader";
import React from "react";

const Page = () => {
    return (
        <>
            <HeaderShared>
                <TitleHeader title={"댓글 수정하기"} />
            </HeaderShared>
            <SectionShared>
                <ReplyUpdateForm />
            </SectionShared>
        </>
    );
};

export default Page;