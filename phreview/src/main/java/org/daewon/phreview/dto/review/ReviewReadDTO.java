package org.daewon.phreview.dto.review;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.daewon.phreview.domain.ReviewImage;

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

    private List<ReviewImage> reviewImages; // 리뷰 이미지

    // 리뷰 이미지 리스트 설정 메서드
    public void setReviewImages(List<ReviewImage> reviewImages) {
        this.reviewImages = reviewImages;
    }

    public int getStar() {
        return star != 0 ? star : 1; // 별점이 0인 경우 기본값으로 1을 사용
    }
}
