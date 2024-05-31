import React, { ReactNode } from "react";

const TextLarge = ({ children }: { children: ReactNode }) => {
    return (
        <h1 className={"text-large font-medium"}>{children}</h1>
    );
};

export default TextLarge;