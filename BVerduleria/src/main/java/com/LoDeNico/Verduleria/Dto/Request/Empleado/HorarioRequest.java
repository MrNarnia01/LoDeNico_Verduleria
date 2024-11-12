package com.LoDeNico.Verduleria.Dto.Request.Empleado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HorarioRequest {
    private Long idE;
    private String dia;
    private LocalTime entrada;
    private LocalTime salida;
}
