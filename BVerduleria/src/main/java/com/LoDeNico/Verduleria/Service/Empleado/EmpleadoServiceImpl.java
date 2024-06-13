package com.LoDeNico.Verduleria.Service.Empleado;

import com.LoDeNico.Verduleria.Dto.Request.Empleado.EmpleadoRequest;
import com.LoDeNico.Verduleria.Dto.Response.Empleado.EmpleadoResponse;
import com.LoDeNico.Verduleria.Dto.Response.Empleado.HorarioResponse;
import com.LoDeNico.Verduleria.Entity.Empleado.Empleado;
import com.LoDeNico.Verduleria.Entity.Empleado.Horario;
import com.LoDeNico.Verduleria.Repository.Empleado.EmpleadoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpleadoServiceImpl implements EmpleadoService{

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    private EmpleadoResponse createEmpleadoResponse(Empleado empleado){
        EmpleadoResponse empleadoResponse=new EmpleadoResponse();
        List<HorarioResponse> horarioResponseList = new ArrayList<>();

        empleadoResponse.setId(empleado.getEId());
        empleadoResponse.setNombre(empleado.getNombre());
        empleadoResponse.setApellido(empleado.getApellido());
        empleadoResponse.setCodArea(empleado.getCodArea());
        empleadoResponse.setTel(empleado.getTel());
        empleadoResponse.setMail(empleado.getMail());
        empleadoResponse.setContra(empleado.getContra());

        if(empleado.getHorarios().isEmpty()){
            empleadoResponse.setHorarioResponseList(horarioResponseList);
        }else{
            for(Horario horario: empleado.getHorarios()) {

                HorarioResponse horarioResponse = new HorarioResponse();
                horarioResponse.setId(horario.getHId());
                horarioResponse.setNombre(empleado.getNombre());
                horarioResponse.setApellido(empleado.getApellido());
                horarioResponse.setEntrada(horario.getEntrada());
                horarioResponse.setSalida(horario.getSalida());

                horarioResponseList.add(horarioResponse);
            }
            empleadoResponse.setHorarioResponseList(horarioResponseList);
        }

        return empleadoResponse;

    }

    public List<EmpleadoResponse> getEmpleadoList(){
        List<EmpleadoResponse> empleadoResponseList=new ArrayList<>();
        EmpleadoResponse empleadoResponse=new EmpleadoResponse();
        List<Empleado> empleadoList=empleadoRepository.findAll();
        if(!empleadoList.isEmpty()){
            for(Empleado empleado: empleadoList){
                empleadoResponse=createEmpleadoResponse(empleado);
                empleadoResponseList.add(empleadoResponse);
            }
            return empleadoResponseList;
        }else{
            empleadoResponse.setId(1001L);
            empleadoResponse.setNombre("Error");
            empleadoResponseList.add(empleadoResponse);
            return empleadoResponseList;
        }
    }

    public EmpleadoResponse getEmpleado(Long id){
        EmpleadoResponse empleadoResponse= new EmpleadoResponse();
        Optional<Empleado> empleadoOptional=empleadoRepository.findById(id);
        if(empleadoOptional.isPresent()){
            return createEmpleadoResponse(empleadoOptional.get());
        }else{
            empleadoResponse.setId(1002L);
            empleadoResponse.setNombre("Error");
            return empleadoResponse;
        }
    }

    public EmpleadoResponse createEmpleado(EmpleadoRequest empleadoRequest){
        boolean b=false;
        if(empleadoRequest.getNombre().isBlank()) b=true;
        if(empleadoRequest.getApellido().isBlank()) b=true;
        if(empleadoRequest.getMail().isBlank()) b=true;
        if(empleadoRequest.getContra().isBlank()) b=true;
        else if(empleadoRequest.getContra().length()<8) b=true;
        if(empleadoRequest.getCodArea()==0) b=true;
        if(empleadoRequest.getTel()==0) b=true;
        if(!b){
            Empleado empleado=new Empleado();
            empleado.setNombre(empleadoRequest.getNombre());
            empleado.setApellido(empleadoRequest.getApellido());
            empleado.setMail(empleadoRequest.getMail());
            empleado.setContra(empleadoRequest.getContra());
            empleado.setCodArea(empleadoRequest.getCodArea());
            empleado.setTel(empleadoRequest.getTel());
            empleado = empleadoRepository.save(empleado);
            return createEmpleadoResponse(empleado);
        }else{
            EmpleadoResponse empleadoResponse=new EmpleadoResponse();
            empleadoResponse.setId(1003L);
            empleadoResponse.setNombre("Error");
            return empleadoResponse;
        }
    }

    public int deleteEmpleado(Long id){
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(id);
        if(empleadoOptional.isPresent()){
            Empleado empleado = empleadoOptional.get();
            if(empleado.getHorarios().isEmpty()){
                empleadoRepository.deleteById(id);
                return 0;
            }else return 1004;
        }else {
            return 1002;
        }
    }








}
