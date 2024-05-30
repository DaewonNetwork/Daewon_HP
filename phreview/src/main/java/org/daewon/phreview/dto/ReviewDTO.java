package org.daewon.phreview.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.daewon.phreview.domain.ReviewImage;
import org.daewon.phreview.domain.Users;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {

    private Long reviewId;
    private Long phId;      // 특정한 병원 번호를 선언
    private Long userId;    // 특정한 유저 id를 선언
//    private Users users;

    @NotEmpty
    private String reviewText;

    private int star;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    private List<ReviewImageDTO> reviewImages; // 리뷰 이미지 리스트

    // 리뷰 이미지 리스트 설정 메서드
    public void setReviewImages(List<ReviewImageDTO> reviewImages) {
        this.reviewImages = reviewImages;
    }

}