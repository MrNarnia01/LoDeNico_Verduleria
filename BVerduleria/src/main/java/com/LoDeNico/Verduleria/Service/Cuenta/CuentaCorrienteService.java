package com.LoDeNico.Verduleria.Service.Cuenta;

import com.LoDeNico.Verduleria.Dto.Request.Cuenta.ClienteRequest;
import com.LoDeNico.Verduleria.Dto.Request.Cuenta.CuentaCorrienteRequest;
import com.LoDeNico.Verduleria.Dto.Response.Cuenta.CuentaCorrienteResponse;

import java.util.List;

public interface CuentaCorrienteService {
    public CuentaCorrienteResponse getCuentaCorriente(Long id);
    public List<CuentaCorrienteResponse> getCuentaCorrienteList();
    public int deleteCuentaCorriente(Long id);
    public CuentaCorrienteResponse createCuentaCorrienteWithCliente(CuentaCorrienteRequest cuentaCorrienteRequest);
    public CuentaCorrienteResponse updateCuentaCorriente(CuentaCorrienteRequest cuentaCorrienteRequest);
    public CuentaCorrienteResponse updateFPago(Long id);
    public CuentaCorrienteResponse createCuentaCorrienteWithoutCliente(ClienteRequest clienteRequest);
}
