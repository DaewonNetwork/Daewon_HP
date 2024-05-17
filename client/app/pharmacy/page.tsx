"use client";

import Entitie from "@/(FSD)/entities";
import React from "react"
import { useSearchParams } from "next/navigation";

const page = () => {
    const searchParams = useSearchParams();
    const keyword = searchParams.get("keyword");
    return (
        <Entitie.ui.PharmacyList keyword={keyword || ""}  />
    );
};

export default page;