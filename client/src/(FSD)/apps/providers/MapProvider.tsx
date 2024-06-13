"use client";

import { useRouter } from "next/navigation";
import Script from "next/script";
import React, { useEffect } from "react";

const MapProvider = ({ children }: Readonly<{ children: React.ReactNode; }>) => {
    const router = useRouter();

    useEffect(() => {
        const loadKakaoMap = () => {
            (window as any).kakao.maps.load(() => {
                router.refresh();
            });
        };

        if (typeof (window as any).kakao === "undefined" || typeof (window as any).kakao.maps === "undefined") {
            const script = document.createElement("script");
            script.onload = loadKakaoMap;
            script.src = "//dapi.kakao.com/v2/maps/sdk.js?appkey=2394155f8d491f33d2e132cb5633c658&libraries=services&autoload=false";
            document.head.appendChild(script);
        } else {
            loadKakaoMap();
        }
    }, []);

    return (
        <>
            {children}
            <Script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2394155f8d491f33d2e132cb5633c658&libraries=services&autoload=false" />
        </>
    );
};

export default MapProvider;