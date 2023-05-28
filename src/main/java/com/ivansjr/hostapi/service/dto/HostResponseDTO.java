package com.ivansjr.hostapi.service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HostResponseDTO {

    public HostResponseDTO(String name, String phone, String document, LocalDate birthdate, Double totalAmountStay, Double totalAmountLastStay, Double totalAmountSpentInTheHotel) {
        this.name = name;
        this.phone = phone;
        this.document = document;
        this.birthdate = birthdate;
        this.totalAmountStay = totalAmountStay;
        this.totalAmountLastStay = totalAmountLastStay;
        this.totalAmountSpentInTheHotel = totalAmountSpentInTheHotel;
    }

    private String name;

    private String phone;

    private String document;

    private LocalDate birthdate;

    private Double totalAmountStay;

    private Double totalAmountLastStay;

    private Double totalAmountSpentInTheHotel;

}
