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
    private Long nB;
    private String nombre;
    private Long idProdu;
    private int caja;
    private int cantidad;
}
