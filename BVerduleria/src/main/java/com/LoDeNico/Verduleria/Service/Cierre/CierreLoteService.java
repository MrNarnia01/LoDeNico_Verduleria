package com.LoDeNico.Verduleria.Service.Cierre;

import com.LoDeNico.Verduleria.Dto.Request.Cierre.BusRequest;
import com.LoDeNico.Verduleria.Dto.Request.Cierre.CierreRequest;
import com.LoDeNico.Verduleria.Entity.Cierre.CierreLote;

import java.util.List;

public interface CierreLoteService {
    public CierreLote createCierre(CierreRequest cierreRequest);
    public int deleteCierre(Long id);
    public List<CierreLote> cierreLoteBus(BusRequest busRequest);
}
