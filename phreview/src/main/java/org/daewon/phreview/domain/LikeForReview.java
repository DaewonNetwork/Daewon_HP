package org.daewon.phreview.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class LikeForReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewId")
    private Review review;

    private boolean isLike;

    public LikeForReview(Review review , Users users) {
        this.review = review;
        this.users = users;
        this.isLike = true;
    }

    public void unLikeReview(Review review) {
        this.isLike = false;
    }
}
