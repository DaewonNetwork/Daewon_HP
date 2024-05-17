"use client";

import React, { useRef, useState } from "react";
import styles from "@/(FSD)/shareds/styles/MainContent.module.scss";
import Shared from "@/(FSD)/shareds";
import Entitie from "@/(FSD)/entities";
import { useRouter } from "next/navigation";

const MainContent = () => {
    const { useMapSearch } = Entitie.api.map;
    const [input, setInput] = useState("");
    const router = useRouter();

    return (
        <section className={styles.container}>
            <form className={styles.form} onSubmit={e => {
                e.preventDefault();
            }}>
                <div className={styles.input_box}>
                    <Shared.ui.Input onChange={e => {
                        setInput(e.target.value);
                    }} size={"lg"} errorMessage={<></>} placeholder={"지역, 병원명 검색하기"} name={"map_search"} isClearable={false} endContent={<Shared.ui.Icon size={"lg"} iconType={"search"} />} />
                </div>
                <Shared.ui.Button onClick={_ => {
                    router.push(`/pharmacy?keyword=${encodeURIComponent(input)}`);
                }}>검색</Shared.ui.Button>
            </form>
        </section>
    );
};

export default MainContent;