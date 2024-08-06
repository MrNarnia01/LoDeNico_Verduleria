package com.LoDeNico.Verduleria.Service.Cuenta;

import com.LoDeNico.Verduleria.Dto.Request.Cuenta.CuentaCorrienteRequest;
import com.LoDeNico.Verduleria.Dto.Request.FechaRequest;
import com.LoDeNico.Verduleria.Dto.Request.MontoRequest;
import com.LoDeNico.Verduleria.Dto.Response.Cuenta.CuentaCorrienteResponse;

import java.util.List;

public interface CuentaCorrienteService {
    public CuentaCorrienteResponse getCuentaCorriente(Long id);
    public List<CuentaCorrienteResponse> getCuentaCorrienteList();
    public List<CuentaCorrienteResponse> getCuentaCorrienteListByMonto(MontoRequest montoRequest);
    public int deleteCuentaCorriente(Long id);
    public CuentaCorrienteResponse createCuentaCorriente(CuentaCorrienteRequest cuentaCorrienteRequest);
    public CuentaCorrienteResponse updateCuentaCorriente(CuentaCorrienteRequest cuentaCorrienteRequest, Long id);
    public CuentaCorrienteResponse updateFPago(Long id);
    public List<CuentaCorrienteResponse> getCuentaCorrienteListByFechas(FechaRequest fechaRequest, boolean columna);
}
