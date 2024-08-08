package com.LoDeNico.Verduleria.Service.Empleado;

import com.LoDeNico.Verduleria.Dto.Request.Empleado.HorarioRequest;
import com.LoDeNico.Verduleria.Dto.Response.Empleado.HorarioResponse;
import com.LoDeNico.Verduleria.Entity.Empleado.Empleado;
import com.LoDeNico.Verduleria.Entity.Empleado.Horario;
import com.LoDeNico.Verduleria.Repository.Empleado.EmpleadoRepository;
import com.LoDeNico.Verduleria.Repository.Empleado.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HorarioServiceImpl implements HorarioService{

    @Autowired
    private final HorarioRepository horarioRepository;
    private final EmpleadoRepository empleadoRepository;

    public HorarioServiceImpl(HorarioRepository horarioRepository, EmpleadoRepository empleadoRepository) {
        this.horarioRepository = horarioRepository;
        this.empleadoRepository = empleadoRepository;
    }

    private HorarioResponse createHorarioResponse(Horario horario){
        return new HorarioResponse(horario.getHId(),
                horario.getEmpleado().getPersona().getNombre(),
                horario.getEmpleado().getPersona().getApellido(),
                horario.getEntrada(),
                horario.getSalida());
    }

    public HorarioResponse getHorario(Long id){
        Optional<Horario> horarioOptional = horarioRepository.findById(id);
        if(horarioOptional.isPresent()){
            return createHorarioResponse(horarioOptional.get());
        }else{
            return new HorarioResponse(-1L,"1002","",null,null);
        }
    }

    public int deleteHorario(Long id){
        Optional<Horario> horarioOptional = horarioRepository.findById(id);
        if(horarioOptional.isPresent()){
            Horario horario = horarioOptional.get();
            if(horario.getEntrada().equals(horario.getSalida()))    horarioRepository.deleteById(id);
            else return 1004;
            return 0;
        }else{
            return 1002;
        }
    }

    public HorarioResponse createHorario(HorarioRequest horarioRequest){
        boolean b = true;
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(horarioRequest.getIdE());
        if(empleadoOptional.isEmpty()) b=false;

        if(b){
            Horario horario = new Horario();
            horario.setEmpleado(empleadoOptional.get());
            horario = horarioRepository.save(horario);
            return createHorarioResponse(horario);
        }else{
            return new HorarioResponse(-1L,"1003","",null,null);
        }
    }

    public  HorarioResponse updateHorario(HorarioRequest horarioRequest, Long id) {
        boolean b = true;
        Horario horario = new Horario();

        Optional<Horario> horarioOptional = horarioRepository.findById(id);
        if(horarioOptional.isEmpty())    return new HorarioResponse(-1L, "1002", "", null, null);
        else horario = horarioOptional.get();

        if(!horario.getEntrada().equals(horario.getSalida())) b = false;

        Optional<Empleado> empleadoOptional = empleadoRepository.findById(horarioRequest.getIdE());
        if (empleadoOptional.isEmpty()) b = false;

        if (b) {
            horario = horarioRepository.save(horario);
            return createHorarioResponse(horario);
        } else {
            return new HorarioResponse(-1L, "1003", "", null, null);
        }

    }

    public List<HorarioResponse> getHorarioList(){
        List<Horario> horarioList = horarioRepository.findAll();
        List<HorarioResponse> horarioResponseList = new ArrayList<>();
        if(!horarioList.isEmpty()){
            for (Horario h: horarioList){
                horarioResponseList.add(createHorarioResponse(h));
            }
        }else{
            horarioResponseList.add(new HorarioResponse(-1L,"1002","",null,null));
        }
        return horarioResponseList;
    }

}
