package com.LoDeNico.Verduleria.Service.Producto;

import com.LoDeNico.Verduleria.Dto.Request.MontoRequest;
import com.LoDeNico.Verduleria.Dto.Request.Producto.BoletaRequest;
import com.LoDeNico.Verduleria.Dto.Response.Producto.BoletaResponse;

import java.util.List;

public interface BoletaService {
    public BoletaResponse getBoleta(Long id);
    public List<BoletaResponse> getBoletaList();
    public int deleteBoleta(Long id);
    public List<BoletaResponse> getBoletaListByMonto(MontoRequest montoRequest);
    public List<BoletaResponse> getBoletaListByPago(boolean pago);
    public BoletaResponse createBoleta(BoletaRequest boletaRequest);
    public BoletaResponse updateBoleta(BoletaRequest boletaRequest,Long id);
}
