"use client";

import Script from "next/script";
import React from "react";

const MapProvider = ({ children }: Readonly<{ children: React.ReactNode; }>) => {
    return (
        <>
            {children}
            <Script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2394155f8d491f33d2e132cb5633c658&libraries=services&autoload=false" />
        </>
    );
};

export default MapProvider;