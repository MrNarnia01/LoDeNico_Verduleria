package com.LoDeNico.Verduleria.Dto.Request.Cuenta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CuentaCorrienteRequest {
    private Long idC;
    private double monto;
}
