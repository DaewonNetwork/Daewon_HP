import React from "react";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import UserLogoutButton from "@/(FSD)/features/user/ui/UserLogoutButton";

const Page = () => {
    return (
        <>
            <AppHeader isSearchRegion={false} />
            <div style={{ height: 200 }}></div>
            <UserLogoutButton />
            <footer className={"footer"}>
                <AppFooter />
            </footer>
        </>
    );
};

export default Page;