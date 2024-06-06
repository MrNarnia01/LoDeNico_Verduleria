package com.LoDeNico.Verduleria.Dto.Response.Proveedor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrabajadorResponse {
    private String puesto;
    private String nombre;
    private String apellido;
    private int codArea;
    private int tel;
    private String negocio;
}
