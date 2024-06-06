package com.LoDeNico.Verduleria.Dto.Request.Cuenta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CuentaCorrienteRequest {
    private Long idC;
    private double monto;
    private Date fRegistro;
}
