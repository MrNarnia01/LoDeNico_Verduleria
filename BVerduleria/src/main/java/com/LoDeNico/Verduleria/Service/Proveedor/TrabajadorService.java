package com.LoDeNico.Verduleria.Service.Proveedor;

import com.LoDeNico.Verduleria.Dto.Request.Proveedor.TrabajadorRequest;
import com.LoDeNico.Verduleria.Dto.Response.Proveedor.TrabajadorResponse;

import java.util.List;

public interface TrabajadorService {
    public TrabajadorResponse getTrabajador(Long id);
    public List<TrabajadorResponse> getTrabajadorList();
    public int deleteTrabajador(Long id);
    public TrabajadorResponse createTrabajador(TrabajadorRequest trabajadorRequest);
    public TrabajadorResponse updateTrabajador(TrabajadorRequest trabajadorRequest, Long id);
    public List<TrabajadorResponse> getTrabajadorByNombre(String nombre);
    public List<TrabajadorResponse> getTrabajadorByApellido(String apellido);
    public List<TrabajadorResponse> getTrabajadorByPuesto(String puesto);
}
