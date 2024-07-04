package com.LoDeNico.Verduleria.Dto.Response.Producto;

import com.LoDeNico.Verduleria.Dto.Response.Detalle.DetalleBoletaResponse;
import com.LoDeNico.Verduleria.Dto.Response.PagoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoletaResponse {
    private Long nB;
    private Long idP;
    private boolean paga;
    private Timestamp fRecibo;
    private double monto;
    private List<DetalleBoletaResponse> detalleBoletaResponseList;
    private List<PagoResponse> pagoResponseList;
}
