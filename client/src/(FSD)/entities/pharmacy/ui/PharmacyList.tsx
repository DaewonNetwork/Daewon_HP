"use client";

import { PharmacyType } from "@/(FSD)/shareds/types/pharmacys/Pharmacy.type";
import PharmacyShared from "@/(FSD)/shareds/ui/PharmacyShared";
import React from "react";

const PharmacyList = ({ pharmacyList }: { pharmacyList: PharmacyType[] }) => {
    return (
        <>
            {
                pharmacyList.map((pharmacy, index) => (
                    <React.Fragment key={index}>
                        <PharmacyShared pharmacy={pharmacy} />
                    </React.Fragment>
                ))
            }
        </>
    );
};

export default PharmacyList;