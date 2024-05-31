package org.daewon.phreview.dto.Pharmacy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyEnjoyDTO {

    private Long phId;
    private int enjoyIndex; // 즐겨찾기 인덱스
}
