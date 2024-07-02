package com.LoDeNico.Verduleria.Service.Empleado;

import com.LoDeNico.Verduleria.Dto.Request.Empleado.EmpleadoRequest;
import com.LoDeNico.Verduleria.Dto.Response.Empleado.EmpleadoResponse;

import java.util.List;

public interface EmpleadoService {
    public List<EmpleadoResponse> getEmpleadoList();
    public EmpleadoResponse getEmpleado(Long id);
    public EmpleadoResponse createEmpleado(EmpleadoRequest empleadoRequest);
    public int deleteEmpleado(Long id);
    public EmpleadoResponse updateEmpleado(EmpleadoRequest empleadoRequest, Long id);
}
