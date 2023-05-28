package com.ivansjr.hostapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "check_in")
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private OffsetDateTime entryDate = OffsetDateTime.now();

    private OffsetDateTime exitDate;

    @OneToOne(mappedBy = "checkIn")
    private Host host;

    private Double totalAmountStay;

    private Double totalAmountLastStay;

    private Double totalAmountSpentInTheHotel;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isInTheHotel;
}
