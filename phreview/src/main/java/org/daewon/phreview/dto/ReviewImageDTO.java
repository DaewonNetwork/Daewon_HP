package org.daewon.phreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewImageDTO {

    private String uuid; // 이미지 식별
    private String fileName; // 이미지 파일 이름
    private int ord; // 이미지 순서

}
