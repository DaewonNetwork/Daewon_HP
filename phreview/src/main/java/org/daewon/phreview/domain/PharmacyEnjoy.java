package org.daewon.phreview.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyEnjoy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment와 같은
    private Long PharmacyEnjoyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phId", referencedColumnName = "phId", unique = true)
    private Pharmacy pharmacy;

    private int enjoyIndex; // 즐겨찾기 인덱스
}
