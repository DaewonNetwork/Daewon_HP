package org.daewon.phreview.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

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
    // @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "phId", referencedColumnName = "phId")
    private Pharmacy pharmacy;

    @Column(length = 500, nullable = false)
    private String reviewText;

    private int star =0; // 평점

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @PrePersist
    public void onCreate() {
        createAt = new Date();
    }

    // 리뷰작성 내용 수정
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    // pharmacy 값 설정 -> phId를 받아서 생성
    public void setPharmacy(Long phId) {
        this.pharmacy = Pharmacy.builder().phId(phId).build();
    }

    @OneToMany(mappedBy = "review",
               cascade = {CascadeType.ALL},
               fetch = FetchType.LAZY,
               orphanRemoval = true)
    @Builder.Default
    @BatchSize(size = 20)
    private Set<ReviewImage> imageSet = new HashSet<>();

    public void addImage(String uuid, String fileName) {
        ReviewImage reviewImage = ReviewImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .review(this)
                .ord(imageSet.size())
                .build();
        imageSet.add(reviewImage);
    }

    public void clearImages() {

        imageSet.forEach(reviewImage -> reviewImage.changeBoard(null));

        this.imageSet.clear();

    }
}
