import React from "react";
import styles from "@/(FSD)/shareds/styles/ReviewStyle.module.scss";
import ReviewHeader from "@/(FSD)/widgets/review/ui/ReviewHeader";
import ReviewCreateForm from "@/(FSD)/features/review/ui/ReviewCreateForm";

const Page = () => {
    return (
        <div className={styles.container}>
            <ReviewHeader />
            <ReviewCreateForm />
        </div>
    )
}

export default Page;