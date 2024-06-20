
import Error from "@/(FSD)/widgets/app/ui/Error";
import { Metadata } from "next";
import React from "react";

export const metadata: Metadata = {
    title: "HP - 404",
}

const NotFound = () => {
    return (
        <Error message={"알맞지 않는 약국 정보입니다."} status={404} />
    );
};

export default NotFound;