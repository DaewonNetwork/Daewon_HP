package org.daewon.phreview.domain;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "pharmacy")
public class Review extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "phId", referencedColumnName = "phId")
    private Pharmacy pharmacy;

    @Column(length = 500, nullable = false)
    private String reviewText;

    private int star; // 평점

    // 리뷰작성 내용 수정
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    // pharmacy 값 설정 -> phId를 받아서 생성
    public void setPharmacy(Long phId) {
        this.pharmacy = Pharmacy.builder().phId(phId).build();
    }

    public void setUsers(Long userId) {
        this.users = Users.builder().userId(userId).build();
    }
}