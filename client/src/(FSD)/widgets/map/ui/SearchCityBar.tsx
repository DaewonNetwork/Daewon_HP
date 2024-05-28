"use client";

import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import { cityList } from "../consts/cityList";
import TextTitleShared from "@/(FSD)/shareds/ui/TextTitleShared";
import { Chip } from "@nextui-org/chip";
import Slider from "react-slick";

const SearchCity = () => {
    var settings = {
        dots: true,
        infinite: false,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
    };

    return (
        <section className={styles.search_city_container}>
            <TextTitleShared>지역별 검색하기</TextTitleShared>
            <div className={styles.city_list}>
                <>
                    {
                        cityList.map((item, index) => (
                            <React.Fragment key={index}>
                                
                            </React.Fragment>
                        ))
                    }
                </>
            </div>
        </section>
    )
}

export default SearchCity;