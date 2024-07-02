package com.LoDeNico.Verduleria.Dto.Request.Producto;

import com.LoDeNico.Verduleria.Dto.Request.Detalle.DetalleRequest;

import java.util.Date;
import java.util.List;

public class BoletaRequest {
    private Long idP;
    private Date fRecibo;
    private List<DetalleRequest> detalleBoletaRequestList;
}
