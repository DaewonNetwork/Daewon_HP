package org.daewon.phreview.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @JoinColumn(name = "userId", referencedColumnName = "userId")

    @OnDelete(action = OnDeleteAction.CASCADE) // 유저 삭제하면 리뷰 같이 삭제
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "phId", referencedColumnName = "phId")

    private Pharmacy pharmacy;

    @Column(length = 500, nullable = false)
    private String reviewText;

    private int star; // 평점

    @JoinColumn(name = "fileName", referencedColumnName = "fileName")
    private String reviewImage;

    // 리뷰작성 내용 수정
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    // pharmacy 값 설정 -> phId를 받아서 생성
    public void setPharmacy(Long phId) {
        this.pharmacy = Pharmacy.builder().phId(phId).build();
    }

    // setter설정
    public void setUsers(Long userId) {
        this.users = Users.builder().userId(userId).build();
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }
    
    public void setStar(int star) {
        this.star = star;
    }
    
    public void setReviewImage(String reviewImage) {
        this.reviewImage = reviewImage;
    }

    @OneToMany(mappedBy = "review",
               cascade = {CascadeType.ALL},
               fetch = FetchType.LAZY,
               orphanRemoval = true)
    @Builder.Default
    @BatchSize(size = 20)
    private Set<ReviewImage> imageSet = new HashSet<>(); // 리뷰에 연결된 이미지를 하나의 그룹으로 묶어서 관리

    public void addImage(String uuid, String fileName) {
        ReviewImage reviewImage = ReviewImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .review(this)
                .ord(imageSet.size())
                .build();
        imageSet.add(reviewImage); // 새 이미지를 리뷰의 이미지 그룹에 추가
    }

    public void clearImage() {
        imageSet.forEach(reviewImage -> reviewImage.changeBoard(null)); // 리뷰와 연결된 모든 이미지의 review참조를 null로 설정

        this.imageSet.clear();
    }
}
