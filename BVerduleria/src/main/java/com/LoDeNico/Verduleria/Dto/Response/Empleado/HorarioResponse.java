package com.LoDeNico.Verduleria.Dto.Response.Empleado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HorarioResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private Timestamp entrada;
    private Timestamp salida;
}
