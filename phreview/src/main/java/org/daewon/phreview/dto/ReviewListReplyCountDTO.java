package org.daewon.phreview.dto;

import lombok.Data;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
public class ReviewListReplyCountDTO {
    private Long reviewId; // 리뷰 ID
    private String title; // 리뷰 제목
    private String writer; // 작성자
    private Date createAt; // 등록 날짜

    private Long replyCount; // 댓글 수
    private int star; // 별점
    private Long phId; // 병원 ID
}



