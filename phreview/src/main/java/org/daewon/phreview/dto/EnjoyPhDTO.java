package org.daewon.phreview.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnjoyPhDTO {

    private Long enjoyId;
    private Long phId;
    private Long userId;
    private boolean isEnjoy;
}
