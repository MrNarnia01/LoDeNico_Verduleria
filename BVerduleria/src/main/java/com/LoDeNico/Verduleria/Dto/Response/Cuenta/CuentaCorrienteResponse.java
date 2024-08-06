package com.LoDeNico.Verduleria.Dto.Response.Cuenta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

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
