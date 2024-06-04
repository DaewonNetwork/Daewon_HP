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
public class PharmacyStar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment와 같은
    private Long PharmacyStarId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phId", referencedColumnName = "phId")
    private Pharmacy pharmacy;

    private double starAvg; // 별점 평균
    private double starTotal; // 별점 총합
}
