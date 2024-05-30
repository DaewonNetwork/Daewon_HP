import React from "react";
import styles from "@/(FSD)/shareds/styles/ProfileStyle.module.scss";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";

const Page = () => {
    return (
        <div>
            <div>
                <AppHeader isSearchRegion={false} />
            </div>
            <div className={"footer"}>
                <AppFooter />
            </div>
        </div>
    );
};

export default Page;