import React from "react"
import { useRouter } from "next/navigation";
import styles from "@/(FSD)/shareds/styles/Pharmacy.module.scss";
import PharmacyEnjoyBtn from "@/(FSD)/features/pharmacy/ui/PharmacyEnjoyBtn";
import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
import ItemShared from "@/(FSD)/shareds/ui/ItemShared";
import TextLargeShared from "@/(FSD)/shareds/ui/TextLargeShared";
import TextMediumShared from "@/(FSD)/shareds/ui/TextMediumShared";

const PharmacyItem = ({ pharmacy, parentRefetch, rank }: { pharmacy: PharmacyType; parentRefetch?: any; rank?: string }) => {
    if (!pharmacy) return;
    const router = useRouter();
    
    return (
        <div
            onClick={_ => {
                router.push(`/pharmacy/${pharmacy.phId}`);
            }}
            className={styles.pharmacy_item}
        >
            <ItemShared>
                <div className={styles.top_item}>
                    <TextLargeShared>{rank} {pharmacy.phName}</TextLargeShared>
                    <PharmacyEnjoyBtn parentRefetch={parentRefetch} phId={pharmacy.phId} defaultLikeActive={pharmacy.enjoy} />
                </div>
                <div className={styles.btm_item}>
                    <TextMediumShared>{pharmacy.phAdd}</TextMediumShared>
                    <TextMediumShared>{pharmacy.phTel}</TextMediumShared>
                </div>
            </ItemShared>
        </div>
    )
}

export default PharmacyItem;