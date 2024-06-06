package com.LoDeNico.Verduleria.Dto.Request.Cuenta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {
    private String nombre;
    private String apellido;
    private int codArea;
    private int tel;
    private String calle;
    private int altura;
}
