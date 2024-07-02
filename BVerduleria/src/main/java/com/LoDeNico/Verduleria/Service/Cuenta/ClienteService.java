package com.LoDeNico.Verduleria.Service.Cuenta;

import com.LoDeNico.Verduleria.Dto.Request.Cuenta.ClienteRequest;
import com.LoDeNico.Verduleria.Dto.Response.Cuenta.ClienteResponse;

import java.util.List;

public interface ClienteService {
    public ClienteResponse getCliente(Long id);
    public List<ClienteResponse> getClienteList();
    public List<ClienteResponse> getClienteListByNombre(String nombre);
    public List<ClienteResponse> getClienteListByApellido(String apellido);
    public List<ClienteResponse> getClienteListByCalle(String calle);
    public ClienteResponse createCliente(ClienteRequest clienteRequest);
    public int deleteCliente(Long id);
    public ClienteResponse updateCliente(ClienteRequest clienteRequest, Long id);
}
