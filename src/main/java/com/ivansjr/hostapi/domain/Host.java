package com.ivansjr.hostapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "host")
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    private String name;

    private String phone;

    private String document;

    private LocalDate birthdate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_check_in", referencedColumnName = "id")
    @JsonIgnore
    private CheckIn checkIn;

    @PrePersist
    public void prePersist() {
        if (checkIn != null) {
            checkIn.setHost(this);
            checkIn.setEntryDate(OffsetDateTime.now());
            checkIn.setIsInTheHotel(true);
        }
    }
}
