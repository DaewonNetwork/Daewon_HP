package org.daewon.phreview.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewImageDTO {

    private String uuid;
    private String fileName;
    private int ord;

}
