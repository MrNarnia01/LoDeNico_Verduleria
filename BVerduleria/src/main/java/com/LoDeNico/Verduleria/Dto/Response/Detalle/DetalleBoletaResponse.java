package com.LoDeNico.Verduleria.Dto.Response.Detalle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleBoletaResponse {
    private Long id;
    private Long boleta;
    private String nombre;
    private boolean unit;
    private int caja;
    private int cantidad;
}
