package com.LoDeNico.Verduleria.Dto.Response.Detalle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoResponse {
    private Long id;
    private Long idP;
    private String nombre;
    private boolean unit;
    private Long idProdu;
    private int caja;
    private int cantidad;
}
