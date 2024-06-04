import ReplyContainer from "@/(FSD)/entities/reply/ui/ReplyContainer";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";
import React from "react";

const Page = () => {
    return (

        <>
            <HeaderShared>
                <div>댓글 달기</div>
            </HeaderShared>
            <SectionShared>
                <ReplyContainer />
            </SectionShared>
        </>
    );
};

export default Page;