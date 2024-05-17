"use client";

import React from "react";
import styles from "@/(FSD)/shareds/styles/MainContent.module.scss";
import { useMapSearch } from "@/(FSD)/entities/map/api/useMapSearch";

const MainContent = () => {

    const { data } = useMapSearch("서울");

    console.log(data);
    

    return (
        <section className={styles.container}>

        </section>
    );
};

export default MainContent;