package org.daewon.phreview.dto.Pharmacy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyStarRankListDTO {
    private String phName; // 병원 이름
    private String phTel; // 병원 전화번호
    private String phAdd; // 병원 주소
    private double starAvg;
}
