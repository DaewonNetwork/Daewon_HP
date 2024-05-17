package org.daewon.phreview.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EnjoyPh extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enjoyId;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "phId", referencedColumnName = "phId")
    private Pharmacy pharmacy;

    private boolean isEnjoy;
}
