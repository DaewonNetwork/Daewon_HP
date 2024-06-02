import React from "react";
import ReviewHeader from "@/(FSD)/widgets/review/ui/ReviewHeader";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import ReviewCreateForm from "@/(FSD)/features/review/ui/ReviewCreateForm";

const Page = () => {
    return (
        <>
            <HeaderShared>
                <ReviewHeader />
            </HeaderShared>
            <SectionShared>
                <ReviewCreateForm />
            </SectionShared>
        </>
    )
}

export default Page;