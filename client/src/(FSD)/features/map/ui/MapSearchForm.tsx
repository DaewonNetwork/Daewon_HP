"use client";

import React from "react";
import styles from "@/(FSD)/shareds/styles/HomeStyle.module.scss";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";

const MapSearchForm = () => {
    const schema = z.object({
        word: z.string().min(1)
    });

    const { control, handleSubmit, formState: { errors, isValid, submitCount } } = useForm({
        resolver: zodResolver(schema),
        mode: "onChange"
    });

    return (
        <form className={styles.form}>
            
        </form>
    )
}

export default MapSearchForm;