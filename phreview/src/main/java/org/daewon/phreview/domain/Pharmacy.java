package org.daewon.phreview.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment와 같은
    private Long phId;
    private String phName; // 병원 이름
    private String phTel; // 병원 전화번호
    private String phAdd; // 병원 주소
    private String timeWeekStartDate; // 평일 시작
    private String timeWeekEndDate; // 평일 종료
    private String timeSatStartDate; // 토요일 시작
    private String timeSatEndDate; // 토요일 종료
    private String timeHoliStartDate; // 공휴일 시작
    private String timeHoliEndDate; // 공휴일 종료
    private double phX;  // 경도
    private double phY;  // 위도
    private int enjoyIndex; // 즐겨찾기 인덱스
    private int starIndex; // 별점 인덱스getPhPageIndex
}

