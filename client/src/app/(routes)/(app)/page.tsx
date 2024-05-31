import React from "react";
import MapSearchForm from "@/(FSD)/features/pharmacy/ui/MapSearchForm";
import PhRankEnjoy from "@/(FSD)/entities/pharmacy/ui/PhRankEnjoy";
import PhRankStar from "@/(FSD)/entities/pharmacy/ui/PhRankStar";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";

const Page = () => {
    return (
        <>
            <HeaderShared>
                <InnerShared>
                    <MapSearchForm />
                </InnerShared>
            </HeaderShared>
            <>
                <PhRankEnjoy />
                <PhRankStar />
            </>
        </>
    )
}

export default Page;