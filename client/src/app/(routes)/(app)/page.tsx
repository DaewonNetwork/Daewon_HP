import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import banner from "@p/images/banner.png";
import Image from "next/image";

const Page = () => {
    return (
        <div className={styles.container}>
            <div className={styles.banner_box}>
                <Image className={styles.banner_image} src={banner} alt={"banner"} />
                <div className={styles.banner_inner}>
                    <h1 className={"text-logo-size font-semibold"}>원하는 지역으로 약국의 정보를 받아보세요!</h1>
                </div>
            </div>
        </div>
    )
}

export default Page;