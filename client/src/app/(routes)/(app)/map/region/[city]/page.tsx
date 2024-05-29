"use client";

import React, { useEffect } from "react";
import { useParams } from "next/navigation";
import { useSearchRegion } from "@/(FSD)/entities/map/api/search/useSearchRegion";
import { PharmacyType } from "@/(FSD)/shareds/types/Pharmacy.type";
import { useInView } from "react-intersection-observer";

const Page = () => {
    const { city } = useParams<{ city: string }>();

    const { pharmacyList, fetchNextPage } = useSearchRegion(city);
    
    
    const { ref, inView } = useInView();

    useEffect(() => {
      if (inView) {
        fetchNextPage();
      }
    }, [inView]);    

    return (
        <div>
            {
                pharmacyList.map((pharmacy, index) => (
                    <React.Fragment key={index}>
                        <div style={{ height: "500px" }}>
                            {pharmacy.phName}
                        </div>
                    </React.Fragment>
                ))
            }
            <div ref={ref} />
        </div>
    );
};

export default Page;