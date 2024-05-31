import { Button } from "@nextui-org/button";
import { Input } from "@nextui-org/input";
import React from "react";
import type { FileInputType } from "../types/FileInput.type";

const FileInputShared = ({ id, children, setFile, ...props }: FileInputType) => {
    return (
        <>
            <input onChange={e => setFile(e.target)} id={id} style={{ display: "none" }} type={"file"} />
            <label style={{ display: "block" }} htmlFor={id}><Button style={{ zIndex: -1 }} {...props}>{children}</Button></label>
        </>
    )
}

export default FileInputShared;