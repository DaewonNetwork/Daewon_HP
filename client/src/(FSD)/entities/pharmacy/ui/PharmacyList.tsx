"use client";

import { PharmacyListType } from "@/(FSD)/shareds/types/PharmacyList.type";
import PharmacyShared from "@/(FSD)/shareds/ui/PharmacyShared";
import React from "react";

const PharmacyList = ({ pharmacyList }: PharmacyListType) => {
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