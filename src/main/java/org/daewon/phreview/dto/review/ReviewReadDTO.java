package org.daewon.phreview.dto.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.daewon.phreview.domain.ReviewImage;
import org.hibernate.Hibernate;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewReadDTO {

    private Long reviewId;
    private String phName;
    private String userName;

    @NotEmpty
    private String reviewText;

    @NotEmpty
    private String reviewTitle;

    private boolean isReview;


    private int star;

    private int likeIndex;
    private int replyIndex;

    private String createAt;
    private String updateAt;
    

    public int getStar() {
        return star != 0 ? star : 1; // 별점이 0인 경우 기본값으로 1을 사용
    }
}
