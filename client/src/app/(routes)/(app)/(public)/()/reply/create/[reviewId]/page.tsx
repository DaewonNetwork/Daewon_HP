import ReplyContainer from "@/(FSD)/entities/reply/ui/ReplyContainer";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";
import TitleHeader from "@/(FSD)/widgets/app/ui/TitleHeader";
import React from "react";

const Page = () => {
    return (

        <>
            <HeaderShared>
                <TitleHeader title={"댓글 작성하기"} />
            </HeaderShared>
            <SectionShared>
                <ReplyContainer />
            </SectionShared>
        </>
    );
};

export default Page;