
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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @ManyToOne
    @JoinColumn(name = "reviewId", referencedColumnName = "reviewId")
    @OnDelete(action = OnDeleteAction.CASCADE) // 리뷰 삭제시 답글도 삭제
    private Review review;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users users;

    @Column(length = 255)
    private String replyText;


    // 리뷰 댓글 작성 내용 수정
    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    // pharmacy 값 설정 -> phId를 받아서 생성    // pharmacy 값 설정하는거 맞나요?
    // review 값 설정 -> reviewId를 받아서 생성
    public void setReview(Long reviewId) {
        this.review = review.builder().reviewId(reviewId).build();
    }

    // users 값 설정 -> userId를 받아서 생성
    public void setUsers(Long userId) {
        this.users = Users.builder().userId(userId).build();
    }
}
