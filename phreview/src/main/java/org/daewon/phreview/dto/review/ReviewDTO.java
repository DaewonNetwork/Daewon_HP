package org.daewon.phreview.dto.review;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {


    private Long phId;      // 특정한 병원 번호를 선언
    
    @NotEmpty
    private String reviewText;

    @NotEmpty
    private String reviewTitle;

    private int star;



    private Long replyCount; // 댓글 수

    private LocalDateTime createAt;

    private List<ReviewImageDTO> reviewImages; // 리뷰 이미지 리스트

    // 리뷰 이미지 리스트 설정 메서드
    public void setReviewImages(List<ReviewImageDTO> reviewImages) {
        this.reviewImages = reviewImages;
    }

    public int getStar() {
        return star != 0 ? star : 1; // 별점이 0인 경우 기본값으로 1을 사용
    }

}