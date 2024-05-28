package org.daewon.phreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewListAllDTO {

    private Long review_Id;
    private Long user_Id;
    private String review_text;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    private List<ReviewImageDTO> reviewImages;

}
