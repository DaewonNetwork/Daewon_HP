package org.daewon.phreview.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EnjoyPh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enjoyId;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "phId", referencedColumnName = "phID")
    private Pharmacy pharmacy;

    private boolean isEnjoy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @PrePersist
    protected void onCreate() {
        createAt = new Date();
    }
}
