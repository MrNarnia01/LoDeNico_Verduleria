package com.LoDeNico.Verduleria.Dto.Response.Empleado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HorarioResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String dia;
    private LocalTime entrada;
    private LocalTime salida;
}
