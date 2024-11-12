package com.LoDeNico.Verduleria.Service.Empleado;

import com.LoDeNico.Verduleria.Dto.Request.Empleado.EmpleadoRequest;
import com.LoDeNico.Verduleria.Dto.Request.Empleado.SignUpRequest;
import com.LoDeNico.Verduleria.Dto.Response.Empleado.EmpleadoResponse;
import com.LoDeNico.Verduleria.Dto.Response.Empleado.SignUpResponse;
import com.LoDeNico.Verduleria.Entity.Empleado.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    public List<EmpleadoResponse> getEmpleadoList();
    public EmpleadoResponse getEmpleado(Long id);
    public EmpleadoResponse createEmpleado(EmpleadoRequest empleadoRequest);
    public int deleteEmpleado(Long id);
    public EmpleadoResponse updateEmpleado(EmpleadoRequest empleadoRequest, Long id);
    public void inicializarEmpleado();
    public boolean signUp(SignUpRequest signUpRequest);
    public SignUpResponse recover(SignUpRequest signUpRequest);
    public boolean newContra(SignUpRequest signUpRequest, Long id);
}
