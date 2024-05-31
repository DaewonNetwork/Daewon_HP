"use client";

import React, { useEffect } from "react";
import { useReadPharmacy } from "../api/useReadPharmacy";
import { useParams } from "next/navigation";
import IconShared from "@/(FSD)/shareds/ui/IconShared";
import { Chip } from "@nextui-org/chip";
import { PharmacyInfoType } from "@/(FSD)/shareds/types/pharmacys/PharmacyInfo.type";
import useUserStore from "@/(FSD)/shareds/stores/useUserStore";
import { Button } from "@nextui-org/button";
import Link from "next/link";
import ReviewList from "../../review/ui/ReviewList";

const PharmacyInfo = () => {
    const { phId } = useParams<{ phId: string }>();

    const { data, isError, isLoading, refetch } = useReadPharmacy(Number(phId));

    const user = useUserStore().user;

    const pharmacy: PharmacyInfoType = data;

    useEffect(() => {
        refetch();
    }, [phId]);

    if (isError) return <></>;
    if (isLoading) return <></>;

    return (
        <>
            <Chip variant={"bordered"}><p className={`text-medium font-medium`}><IconShared iconType={"star"} /><span>{pharmacy.starAvg}</span></p></Chip>
            <Chip variant={"bordered"}><p className={"text-medium font-medium"}>리뷰 {pharmacy.reviewIndex}</p></Chip>
            <Chip variant={"bordered"}><p className={"text-medium font-medium"}>즐겨찾기 {pharmacy.enjoyIndex}</p></Chip>
            <Button as={Link} href={`/review/create/${phId}`} color={"primary"} size={"lg"} fullWidth>리뷰 작성하기</Button>
            <ReviewList />
        </>
    );
};

export default PharmacyInfo;