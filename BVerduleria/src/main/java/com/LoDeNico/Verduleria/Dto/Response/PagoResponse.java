package com.LoDeNico.Verduleria.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PagoResponse {
    private Long id;
    private Long idB;
    private String tipo;
    private Timestamp fPago;
    private double monto;
}
