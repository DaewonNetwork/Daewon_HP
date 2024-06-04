package org.daewon.phreview.dto.pharmacy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnjoyPhDTO {
    private Long phId;
    private boolean isEnjoy; // 즐겨찾기 여부
}
