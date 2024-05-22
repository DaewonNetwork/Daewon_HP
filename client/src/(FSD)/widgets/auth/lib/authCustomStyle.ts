import classNames from "classnames/bind";
import AuthStyle from "@/(FSD)/shareds/styles/AuthStyle.module.scss";

const signinClassNames = classNames.bind(AuthStyle);

export const authHeaderStyle = (type: "signin" | "signup") => {
    const classNames = signinClassNames({
        "signin_header": type === "signin",
        "signup_header": type === "signup"
    });

    return classNames;
};