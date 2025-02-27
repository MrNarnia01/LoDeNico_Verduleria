package com.LoDeNico.Verduleria.Dto.Response.Cuenta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CuentaCorrienteResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private double monto;
    private Timestamp fRegistro;
    private Timestamp fPago;
}
