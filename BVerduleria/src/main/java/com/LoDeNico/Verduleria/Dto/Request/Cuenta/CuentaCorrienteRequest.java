package com.LoDeNico.Verduleria.Dto.Request.Cuenta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CuentaCorrienteRequest {
    private Long id;
    private double monto;
}
