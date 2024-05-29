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

    private Long review_Id; // 리뷰 식별
    private Long user_Id; // 리뷰 작성자 식별
    private String review_text; // 리뷰 본문 내용
    private LocalDateTime createAt; // 리뷰 생성시간
    private LocalDateTime updateAt; // 리뷰 수정시간

    private List<ReviewImageDTO> reviewImages; // 리뷰와 연관된 이미지 목록

}
