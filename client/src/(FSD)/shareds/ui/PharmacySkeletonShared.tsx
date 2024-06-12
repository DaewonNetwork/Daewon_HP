import React from "react";
import ItemShared from "./ItemShared";
import styles from "@/(FSD)/shareds/styles/SkeletonStyle.module.scss";
import { Skeleton } from "@nextui-org/skeleton";

const PharmacySkeletonShared = () => {
    return (
        <div className={styles.pharmacy}>
            <ItemShared>
                <Skeleton className={`rounded-small ${styles.pharmacy_title}`} />
                <Skeleton className={`rounded-small ${styles.pharmacy_add}`} />
                <Skeleton className={`rounded-small ${styles.pharmacy_num}`} />
            </ItemShared>
        </div>
    )
}

export default PharmacySkeletonShared;