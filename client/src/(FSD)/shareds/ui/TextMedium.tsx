import React, { ReactNode } from "react";

const TextMedium = ({ children }: { children: ReactNode }) => {
    return (
        <p className={"text-medium"}>{children}</p>
    );
};

export default TextMedium;