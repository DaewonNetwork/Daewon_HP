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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "phId", referencedColumnName = "phID")
    private Pharmacy pharmacy;

    @Column(length = 500, nullable = false)
    private String reviewText;
//    @Column(length = 50, nullable = false)
//    private String writer;

    private int star = 0; // 평점

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @PrePersist
    public void onCreate() {
        createAt = new Date();
    }

    // 리뷰작성 내용 수정
    public void changeText(String reviewText) {
        this.reviewText = reviewText;
    }

    // pharmacy 값 설정 -> phId를 받아서 생성
    public void setPharmacy(Long phId) {
        this.pharmacy = Pharmacy.builder().phID(phId).build();
    }
}
