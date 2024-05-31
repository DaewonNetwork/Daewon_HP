import React, { ReactNode } from "react";

const TextXlarge = ({ children }: { children: ReactNode }) => {
    return (
        <h1 className={"text-xlarge font-semibold"}>{children}</h1>
    );
};

export default TextXlarge;