package org.daewon.phreview.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "review")
public class ReviewImage implements Comparable<ReviewImage> {

    @Id
    private String uuid; // 이미지 식별
    private String fileName; // 이미지 파일 이름
    private int ord; // 이미지 순서

    @ManyToOne
    private Review review;

    @Override
    public int compareTo(ReviewImage other) {
        return this.ord - other.ord;
    }

    public void changeBoard(Review review) {
        this.review = review;
    }
}
