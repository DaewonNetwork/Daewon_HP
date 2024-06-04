"use client";

import React from "react";
import { PharmacyInfoType } from "@/(FSD)/shareds/types/pharmacys/PharmacyInfo.type";
import TextMediumShared from "@/(FSD)/shareds/ui/TextMediumShared";
import TextXlargeShared from "@/(FSD)/shareds/ui/TextXlargeShared";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import styles from "@/(FSD)/shareds/styles/Pharmacy.module.scss";
import { Chip } from "@nextui-org/chip";
import StarShared from "@/(FSD)/shareds/ui/StarShared";
import PharmacyEnjoyBtn from "@/(FSD)/features/pharmacy/ui/PharmacyEnjoyBtn";
import { Table, TableHeader, TableColumn, TableBody, TableRow, TableCell } from "@nextui-org/table";

const PharmacyInfo = ({ pharmacy, parentRefetch }: { pharmacy: PharmacyInfoType; parentRefetch?: any }) => {
    return (
        <div className={styles.pharmacy_info}>
            <InnerShared>
                <div className={styles.pharmacy_header}>
                    <div className={styles.top_item}>
                        <TextXlargeShared>{pharmacy.phName}</TextXlargeShared>
                        <PharmacyEnjoyBtn parentRefetch={parentRefetch} defaultLikeActive={pharmacy.enjoy} phId={pharmacy.phId} />
                    </div>
                    <div className={styles.btm_item}>
                        <TextMediumShared>{pharmacy.phAdd}</TextMediumShared>
                    </div>
                </div>
                <div className={styles.pharmacy_content}>
                    <Chip size={"lg"} variant={"bordered"}><StarShared isActive={true} /><TextMediumShared>{pharmacy.starAvg}</TextMediumShared></Chip>
                    <Chip size={"lg"} variant={"bordered"}><TextMediumShared>리뷰 {pharmacy.reviewIndex}</TextMediumShared></Chip>
                    <Chip size={"lg"} variant={"bordered"}><TextMediumShared>즐겨찾기 {pharmacy.enjoyIndex}</TextMediumShared></Chip>
                </div>
                <div className={styles.pharmacy_date}>
                    <Table aria-label="Example static collection table">
                        <TableHeader>
                            <TableColumn>구분</TableColumn>
                            <TableColumn>시작 시간</TableColumn>
                            <TableColumn>종료 시간</TableColumn>
                        </TableHeader>
                        <TableBody>
                            <TableRow key="1">
                                <TableCell>평일</TableCell>
                                <TableCell>{pharmacy.timeWeekStartDate}</TableCell>
                                <TableCell>{pharmacy.timeWeekEndDate}</TableCell>
                            </TableRow>
                            <TableRow key="2">
                                <TableCell>토요일</TableCell>
                                <TableCell>{pharmacy.timeSatStartDate}</TableCell>
                                <TableCell>{pharmacy.timeSatEndDate}</TableCell>
                            </TableRow>
                            <TableRow key="3">
                                <TableCell>공휴일</TableCell>
                                <TableCell>{pharmacy.timeHoliStartDate || "없음"}</TableCell>
                                <TableCell>{pharmacy.timeHoliEndDate || "없음"}</TableCell>
                            </TableRow>
                        </TableBody>
                    </Table>
                </div>
            </InnerShared>
        </div>
    );
};

export default PharmacyInfo;