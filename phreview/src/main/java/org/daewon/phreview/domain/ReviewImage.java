package org.daewon.phreview.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "review")
public class ReviewImage implements Comparable<ReviewImage>{

    @Id
    private String uuid;

    private String fileName;

    private int ord;

    private String originalName;

    @ManyToOne
    @JoinColumn(name = "reviewId", referencedColumnName = "reviewId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Review review;


    @Override
    public int compareTo(ReviewImage ord) {
        return this.ord - ord.ord;
    }

    public void changeBoard(Review review) {
        this.review = review;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setFileName(String originalName) {
        this.originalName = originalName;
    }
}
