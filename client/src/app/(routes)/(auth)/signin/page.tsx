import SigninForm from "@/(FSD)/features/auth/ui/SigninForm";
import React from "react";
import styles from "@/(FSD)/shareds/styles/SigninStyle.module.scss";

const Page = () => {
    return (
        <div className={styles.container}>
            <SigninForm />
        </div>
    );
};

export default Page;