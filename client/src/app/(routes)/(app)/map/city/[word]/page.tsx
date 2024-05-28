"use client";

import { useSearchCity } from "@/(FSD)/entities/map/api/search/useSearchCity";
import { useParams } from "next/navigation";
import Script from "next/script";
import React, { useEffect } from "react";
import { Map } from "react-kakao-maps-sdk";

const Page = () => {
    const { word } = useParams<{ word: string }>();

    const { data } = useSearchCity(word);

    console.log(data);

    useEffect(() => {

    }, []);
    
    
    return (
        <div>
            <Map
                center={{ lat: 33.5563, lng: 126.79581 }}
                style={{ width: "200px", height: "200px" }}
                level={3}
            >

            </Map>
            <Script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2394155f8d491f33d2e132cb5633c658&libraries=services&autoload=false" />
        </div>
    );
};

export default Page;