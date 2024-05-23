package org.daewon.phreview.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @ManyToOne
    @JoinColumn(name = "reviewId", referencedColumnName = "reviewId")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users users;

    @Column(length = 255)
    private String replyText;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @PrePersist
    protected void onCreate() {
        createAt = new Date();
    }

    // 리뷰 댓글 작성 내용 수정
    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    // pharmacy 값 설정 -> phId를 받아서 생성
    public void setReview(Long reviewId) {
        this.review = review.builder().reviewId(reviewId).build();
    }
}
