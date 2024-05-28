import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import MapRankEnjoy from "@/(FSD)/entities/map/ui/MapRankEnjoy";
import MapRankStar from "@/(FSD)/entities/map/ui/MapRankStar";


const Page = () => {
    return (
        <div>
            <MapRankEnjoy />
            <MapRankStar />
        </div>
    )
}

export default Page;