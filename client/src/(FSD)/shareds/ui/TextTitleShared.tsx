import React from "react";

const TextTitleShared = ({ children }: { children: React.ReactNode }) => {
    return (
        <h1 className={"text-title text-large font-medium"}>
            { children }
        </h1>
    )
}

export default TextTitleShared;