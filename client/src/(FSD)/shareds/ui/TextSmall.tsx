import React, { ReactNode } from "react";

const TextSmall = ({ children }: { children: ReactNode }) => {
    return (
        <p className={"text-small"}>{children}</p>
    );
};

export default TextSmall;