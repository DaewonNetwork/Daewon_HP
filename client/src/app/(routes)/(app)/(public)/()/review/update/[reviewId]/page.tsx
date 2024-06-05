import ReviewUpdateForm from "@/(FSD)/features/review/ui/ReviewUpdateForm";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";
import TitleHeader from "@/(FSD)/widgets/app/ui/TitleHeader";
import React from "react";

const Page = () => {
    return (
        <>
            <HeaderShared>
                <TitleHeader title={"리뷰 수정하기"} />
            </HeaderShared>
            <SectionShared>
                <ReviewUpdateForm />
            </SectionShared>
        </>
    )
}

export default Page;