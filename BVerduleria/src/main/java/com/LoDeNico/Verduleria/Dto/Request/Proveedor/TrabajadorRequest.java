package com.LoDeNico.Verduleria.Dto.Request.Proveedor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrabajadorRequest {
    private Long id;
    private Long idP;
    private String puesto;
    private String nombre;
    private String apellido;
    private int codArea;
    private int tel;
}
