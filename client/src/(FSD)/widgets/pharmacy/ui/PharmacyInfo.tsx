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
                        <div className={styles.top_text_box}>
                            <TextXlargeShared>{pharmacy.phName}</TextXlargeShared>
                            <TextMediumShared>{pharmacy.phTel || "02-0000-0000"}</TextMediumShared>
                        </div>
                        {!!localStorage.getItem("access_token") && <PharmacyEnjoyBtn parentRefetch={parentRefetch} defaultLikeActive={pharmacy.enjoy} phId={pharmacy.phId} />}
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
                            <TableColumn><TextMediumShared>구분</TextMediumShared></TableColumn>
                            <TableColumn><TextMediumShared>시작 시간</TextMediumShared></TableColumn>
                            <TableColumn><TextMediumShared>종료 시간</TextMediumShared></TableColumn>
                        </TableHeader>
                        <TableBody>
                            <TableRow key="1">
                                <TableCell><TextMediumShared>평일</TextMediumShared></TableCell>
                                <TableCell><TextMediumShared>{pharmacy.timeWeekStartDate || "없음"}</TextMediumShared></TableCell>
                                <TableCell><TextMediumShared>{pharmacy.timeWeekEndDate || "없음"}</TextMediumShared></TableCell>
                            </TableRow>
                            <TableRow key="2">
                                <TableCell><TextMediumShared>토요일</TextMediumShared></TableCell>
                                <TableCell><TextMediumShared>{pharmacy.timeSatStartDate || "없음"}</TextMediumShared></TableCell>
                                <TableCell><TextMediumShared>{pharmacy.timeSatEndDate || "없음"}</TextMediumShared></TableCell>
                            </TableRow>
                            <TableRow key="3">
                                <TableCell><TextMediumShared>공휴일</TextMediumShared></TableCell>
                                <TableCell><TextMediumShared>{pharmacy.timeHoliStartDate || "없음"}</TextMediumShared></TableCell>
                                <TableCell><TextMediumShared>{pharmacy.timeHoliEndDate || "없음"}</TextMediumShared></TableCell>
                            </TableRow>
                        </TableBody>
                    </Table>
                </div>
            </InnerShared>
        </div>
    );
};

export default PharmacyInfo;