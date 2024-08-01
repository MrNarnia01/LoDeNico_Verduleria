package com.LoDeNico.Verduleria.Service;

import com.LoDeNico.Verduleria.Dto.Request.MontoRequest;
import com.LoDeNico.Verduleria.Dto.Request.PagoResquest;
import com.LoDeNico.Verduleria.Dto.Response.PagoResponse;

import java.util.List;

public interface PagoService {
    public PagoResponse getPago(Long id);
    public List<PagoResponse> getPagoList();
    public int deletePago(Long id);
    public PagoResponse createPago(PagoResquest pagoResquest);
    public PagoResponse updatePago(PagoResquest pagoResquest, Long id);
    public List<PagoResponse> getPagoListBus(boolean tip, String tipo, MontoRequest montoRequest);
}
