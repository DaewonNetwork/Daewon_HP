package org.daewon.phreview.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "pharmacy")
public class Review extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phId", referencedColumnName = "phId")
    private Pharmacy pharmacy;

    @Column(length = 500, nullable = false)
    private String reviewText;

    private int star; // 평점

    private int likeIndex; // 좋아요 인덱스
    private int replyIndex; // 좋아요 인덱스

    // 리뷰작성 내용 수정
    public void setReview(String reviewText,int star) {
        this.reviewText = reviewText;
        this.star = star;
    }


    // pharmacy 값 설정 -> phId를 받아서 생성
    public void setPharmacy(Long phId) {
        this.pharmacy = Pharmacy.builder().phId(phId).build();
    }

    public void setUsers(Long userId) {
        this.users = Users.builder().userId(userId).build();
    }


}