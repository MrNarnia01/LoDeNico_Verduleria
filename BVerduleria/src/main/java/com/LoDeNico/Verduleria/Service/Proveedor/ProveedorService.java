package com.LoDeNico.Verduleria.Service.Proveedor;

import com.LoDeNico.Verduleria.Dto.Request.Proveedor.ProveedorRequest;
import com.LoDeNico.Verduleria.Dto.Response.Proveedor.ProveedorResponse;

import java.util.List;

public interface ProveedorService {
    public ProveedorResponse getProveedor(Long id);
    public List<ProveedorResponse> getProveedorList();
    public ProveedorResponse getProveedorByNegocio(String negocio);
    public int deleteProveedor(Long id);
    public ProveedorResponse createProveedor(ProveedorRequest proveedorRequest);
    public ProveedorResponse updateProveedor(ProveedorRequest proveedorRequest, Long id);
}
