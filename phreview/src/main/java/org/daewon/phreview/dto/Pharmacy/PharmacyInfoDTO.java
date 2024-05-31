package org.daewon.phreview.dto.Pharmacy;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyInfoDTO {

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
    private int enjoyIndex;
    private double starAvg;
    private int reviewIndex;
    private EnjoyPhDTO enjoyPhDTO;

}