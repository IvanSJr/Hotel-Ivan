package com.ivansjr.hostapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @JsonIgnore
    private Host host;

    private Double totalAmountStay = 0.0;

    private Double totalAmountLastStay = 0.0;

    private Double totalAmountSpentInTheHotel = 0.0;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isInTheHotel;

    private Boolean hasCar;

    @PreUpdate
    public void updateAmounts() {
        if (!isInTheHotel) {
            double rate = isWeekend() ? 150.0 : 120.0;

            LocalTime currentTime = LocalTime.now();
            LocalTime cutOffTime = LocalTime.of(16, 30);

            if (currentTime.isAfter(cutOffTime)) {
                rate += 25.0;
            } if (hasCar){
                if (isWeekend()) {
                    rate += 20;
                }
                rate += 15;
            }

            totalAmountSpentInTheHotel += rate;
            calculateTotalAmountStay();
        }
    }
    @PostLoad
    public void calculateTotalAmountStay() {
        totalAmountStay = totalAmountLastStay + totalAmountSpentInTheHotel;
        totalAmountLastStay = totalAmountSpentInTheHotel;
    }

    private boolean isWeekend() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}
