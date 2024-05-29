"use client";

import PharmacyShared from "@/(FSD)/shareds/ui/PharmacyShared";
import React from "react";
import type { PharmacyListType } from "../types/PharmacyList.type";

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