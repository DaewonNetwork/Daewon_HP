"use client";

import { useSearchCity } from "@/(FSD)/entities/map/api/search/useSearchCity";
import { useParams } from "next/navigation";
import React from "react";
import { Map } from "react-kakao-maps-sdk";

const Page = () => {
    const { word } = useParams<{ word: string }>();

    const { data } = useSearchCity(word);
    
    return (
        <div>
            <Map
                center={{ lat: 33.5563, lng: 126.79581 }}
                style={{ width: "200px", height: "200px" }}
                level={3}
            >

            </Map>
        </div>
    );
};

export default Page;