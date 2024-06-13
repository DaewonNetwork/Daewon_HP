import React from "react";
import styles from "@/(FSD)/shareds/styles/AppStyle.module.scss";
import { CircularProgress } from "@nextui-org/progress";

const Loading = () => {
    return (
        <div className={styles.loading_bg}>
            <CircularProgress size={"lg"} className={styles.loading_progress} />
        </div>
    );
};

export default Loading;