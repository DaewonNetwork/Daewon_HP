package org.daewon.phreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.daewon.phreview.domain.ReviewImage;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewListAllDTO {

    private Long reviewId;
    private String review_text;
    private Date createAt;
    private long replyCount;

    private List<ReviewImageDTO> reviewImages;

}
