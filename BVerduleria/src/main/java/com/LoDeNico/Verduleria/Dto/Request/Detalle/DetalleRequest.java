package com.LoDeNico.Verduleria.Dto.Request.Detalle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleRequest {
    private Long idP;
    private int caja;
    private int cantidad;
}
