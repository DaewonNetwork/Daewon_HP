import React from "react";
import { Map } from "react-kakao-maps-sdk";
import { MapType } from "../types/Map.type";

const MapShared = ({ onCreate, children, isPending }: MapType) => {
    return (
        <>
            {
                !isPending &&
                <Map style={{ width: "100%", height: "100%" }} level={5} onCreate={onCreate} center={{ lat: 0, lng: 0 }}>
                    {children}
                </Map>
            }
        </>
    )
}

export default MapShared;