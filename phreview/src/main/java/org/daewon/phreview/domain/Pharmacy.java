package org.daewon.phreview.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment와 같은
    private int phID;
    private String phName;
    private String phTel;
    private String phAdd;

    private Time timeWeekStart;
    private Time timeWeekEnd;
    private Time timeSatStart;
    private Time timeSatEnd;
    private Time timeHoliStart;
    private Time timeHoliEnd;

    private double phX;  // 경도
    private double phY;  // 위도

    private int enjoyIndex; //
    private int star;   // 즐겨찾기

}
