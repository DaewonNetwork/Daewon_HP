import React from "react";
import { cityList } from "../consts/cityList";

const SlideCitySelectBar = () => {
    return (
        <div>
            {
                cityList.map((item, index) => (
                    <React.Fragment key={index}>
                        {item}
                    </React.Fragment>
                ))
            }
        </div>
    )
}

export default SlideCitySelectBar;