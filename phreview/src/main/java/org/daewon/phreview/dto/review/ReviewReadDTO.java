package org.daewon.phreview.dto.review;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewReadDTO {

    private Long phId;
    private String phName;
    private String userName;

    @NotEmpty
    private String reviewText;

    private int star;

    private int likeIndex;
    private int replyIndex;

    private String createAt;
    private String updateAt;

    private List<ReviewImageDTO> reviewImages; // 리뷰 이미지 리스트

    // 리뷰 이미지 리스트 설정 메서드
    public void setReviewImages(List<ReviewImageDTO> reviewImages) {
        this.reviewImages = reviewImages;
    }

    public int getStar() {
        return star != 0 ? star : 1; // 별점이 0인 경우 기본값으로 1을 사용
    }
}
