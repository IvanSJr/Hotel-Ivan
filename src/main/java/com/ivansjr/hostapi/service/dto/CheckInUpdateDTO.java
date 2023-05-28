package com.ivansjr.hostapi.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CheckInUpdateDTO {

    @NotNull(message = "Esse campo é obrigatório")
    private Boolean isInTheHotel;

}
