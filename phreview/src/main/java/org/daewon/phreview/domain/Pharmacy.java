package org.daewon.phreview.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

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

    private LocalDateTime timeWeekStart;
    private LocalDateTime timeWeekEnd;
    private LocalDateTime timeSatStart;
    private LocalDateTime timeSatEnd;
    private LocalDateTime timeHoliStart;
    private LocalDateTime timeHoliEnd;

    private float phX;
    private float phY;

    private int enjoyIndex;
    private int star;

}
