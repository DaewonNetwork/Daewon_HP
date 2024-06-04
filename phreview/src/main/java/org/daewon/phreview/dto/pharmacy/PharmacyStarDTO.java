package org.daewon.phreview.dto.pharmacy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyStarDTO {

    private Long phId;
    private double starAvg; // 즐겨찾기 인덱스
}
