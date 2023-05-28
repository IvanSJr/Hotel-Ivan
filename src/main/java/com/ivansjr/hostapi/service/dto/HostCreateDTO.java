package com.ivansjr.hostapi.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HostCreateDTO {

    @NotBlank(message = "O campo nome não pode ser em branco")
    private String name;

    @NotBlank(message = "O campo telefone não pode ser em branco")
    private String phone;

    @NotBlank(message = "O campo cpf não pode ser em branco")
    private String document;

    @NotNull(message = "O campo data de aniversário não pode ser em branco")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @NotNull(message = "O campo tem carro? não pode ser em branco")
    private Boolean hasCar;

}
