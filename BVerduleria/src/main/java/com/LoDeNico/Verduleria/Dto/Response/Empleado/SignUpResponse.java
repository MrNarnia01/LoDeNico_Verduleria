package com.LoDeNico.Verduleria.Dto.Response.Empleado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SignUpResponse {
    private Long id;
    private String nombre;
    private String apellido;
}
