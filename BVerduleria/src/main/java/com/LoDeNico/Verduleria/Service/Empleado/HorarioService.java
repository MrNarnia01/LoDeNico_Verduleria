package com.LoDeNico.Verduleria.Service.Empleado;

import com.LoDeNico.Verduleria.Dto.Request.Empleado.HorarioRequest;
import com.LoDeNico.Verduleria.Dto.Response.Empleado.HorarioResponse;

import java.util.Date;
import java.util.List;

public interface HorarioService {
    public HorarioResponse getHorario(Long id);
    public int deleteHorario(Long id);
    public HorarioResponse createHorario(HorarioRequest horarioRequest);
    public  HorarioResponse updateHorario(HorarioRequest horarioRequest, Long id);
    public List<HorarioResponse> getHorarioList();
}
