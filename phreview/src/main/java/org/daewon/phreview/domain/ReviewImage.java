package org.daewon.phreview.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "review")
public class ReviewImage implements Comparable<ReviewImage>{

    @Id
    private String uuid;
    private String fileName;
    private int ord;

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
