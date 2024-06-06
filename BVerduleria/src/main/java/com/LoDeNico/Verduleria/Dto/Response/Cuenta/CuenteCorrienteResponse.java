package com.LoDeNico.Verduleria.Dto.Response.Cuenta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CuenteCorrienteResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private double monto;
    private Date fRegistro;
    private Date fPago;
}
