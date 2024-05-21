import React from "react";

import SocialContainerWidget from "./(containers)/SocialContainer/ui/SocialContainerWidget";
import AppFooterWidget from "./(footers)/AppFooter/ui/AppFooterWidget";
import AlertModalWidget from "./(modals)/Alert/ui/AlertModalWidget";
import SignupHeaderWidget from "./(headers)/(auth)/SignupHeader/ui/SignupHeaderWidget";
import AppHeaderWidget from "./(headers)/(app)/AppHeader/ui/AppHeaderWidget";

const Widget = () => {
    return (
        <>
        </>
    )
};

Widget.SignupHeader = SignupHeaderWidget;
Widget.SocialContainer = SocialContainerWidget;
Widget.AppFooter = AppFooterWidget;
Widget.AppHeader = AppHeaderWidget;
Widget.AlertModal = AlertModalWidget;
export default Widget;