import { Button } from "@nextui-org/button";
import React, { useState } from "react";
import type { FileInputType } from "../types/FileInput.type";
import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";
import Image from "next/image";
import IconShared from "./IconShared";

const FileInputShared = ({ id, children, setFile, ...props }: FileInputType) => {
    const [preview, setPreview] = useState<string | null>(null);

    const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const file = e.target.files ? e.target.files[0] : null;
        setFile(file);

        if (file) {
            const reader = new FileReader();
            reader.onloadend = () => {
                setPreview(reader.result as string);
            };
            reader.readAsDataURL(file);
        } else {
            setPreview(null);
        }
    };

    return (
        <div className={styles.file_container}>
            <div className={styles.file_input_box}>
                <input onChange={handleFileChange} id={id} type={"file"} />
                <Button className={styles.file_btn} {...props}>
                    <label htmlFor={id}>{children}</label>
                </Button>
            </div>
            {preview &&
                <div className={styles.preview_img_box}>
                    <div className={styles.preview_img_item}>
                        <div className={styles.preview_img_close_btn}>
                            <Button onClick={_ => setPreview(null)} variant={"light"} size={"md"} isIconOnly endContent={<IconShared iconType={"close"} />} />
                        </div>
                        <Image src={preview} alt={"preview"} layout="fill" objectFit="contain" />
                    </div>
                </div>
            }
        </div>
    );
};

export default FileInputShared;