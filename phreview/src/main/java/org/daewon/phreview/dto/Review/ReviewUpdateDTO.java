package org.daewon.phreview.dto.Review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewUpdateDTO {
    private Long reviewId;
    private String reviewText;
    private int star; // 평점
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
