package org.daewon.phreview.domain;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private String fileName;

    private int ord;

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

}
