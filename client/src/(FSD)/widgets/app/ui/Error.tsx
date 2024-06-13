import { ErrorType } from "@/(FSD)/shareds/types/Error.type";
import React from "react";
import styles from "@/(FSD)/shareds/styles/AppStyle.module.scss";
import TextLargeShared from "@/(FSD)/shareds/ui/TextLargeShared";
import LinkBtnShared from "@/(FSD)/shareds/ui/LinkBtnShared";
import TextMediumShared from "@/(FSD)/shareds/ui/TextMediumShared";

const Error = ({ message, status } : ErrorType) => {
    return (
        <div className={styles.error_bg}>
            <div className={styles.error_box}>
                <h1 className={`font-semibold ${styles.error_status}`}>{status}</h1>
                <TextLargeShared>{message}</TextLargeShared>
                <LinkBtnShared size={"md"} variant={"solid"} color={"primary"} href={"/"}><TextMediumShared>홈으로</TextMediumShared></LinkBtnShared>
            </div>
        </div>
    );
};

export default Error;