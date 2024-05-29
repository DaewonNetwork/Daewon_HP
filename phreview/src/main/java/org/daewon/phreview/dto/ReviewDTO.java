package org.daewon.phreview.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String fileName;

    @NotEmpty
    private String reviewText;

    private int star;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    private List<String> fileNames; // 리뷰와 연관된 파일 이름 목록

}
