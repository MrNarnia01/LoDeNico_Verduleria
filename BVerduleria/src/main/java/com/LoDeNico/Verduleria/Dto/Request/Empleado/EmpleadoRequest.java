package com.LoDeNico.Verduleria.Dto.Request.Empleado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoRequest {
    private Long id;
    private String nombre;
    private String apellido;
    private int codArea;
    private int tel;
    private String mail;
    private String contra;
}
