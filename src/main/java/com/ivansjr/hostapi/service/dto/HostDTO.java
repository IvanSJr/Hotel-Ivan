package com.ivansjr.hostapi.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
public class HostDTO {

    @NotBlank(message = "O campo nome não pode ser em branco")
    private String name;

    @NotBlank(message = "O campo nome não pode ser em branco")
    private String phone;

    @NotBlank(message = "O campo nome não pode ser em branco")
    private String document;

    @NotNull(message = "O campo nome não pode ser em branco")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

}
