package com.LoDeNico.Verduleria.Service.Producto;

import com.LoDeNico.Verduleria.Dto.Request.FechaRequest;
import com.LoDeNico.Verduleria.Dto.Request.Producto.LoteRequest;
import com.LoDeNico.Verduleria.Dto.Response.Producto.LoteResponse;

import java.util.List;

public interface LoteService {
    public LoteResponse getLote(Long id);
    public List<LoteResponse> getLoteList();
    public int deleteLote(Long id);
    public LoteResponse createLote(LoteRequest loteRequest);
    public LoteResponse updateLote(LoteRequest loteRequest, Long id);
    public List<LoteResponse> getLoteListByDias(FechaRequest fechaRequest);

}
