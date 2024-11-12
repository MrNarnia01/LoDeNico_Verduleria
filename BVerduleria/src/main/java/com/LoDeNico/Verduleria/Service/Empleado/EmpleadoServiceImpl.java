package com.LoDeNico.Verduleria.Service.Empleado;

import com.LoDeNico.Verduleria.Dto.Request.Empleado.EmpleadoRequest;
import com.LoDeNico.Verduleria.Dto.Request.Empleado.SignUpRequest;
import com.LoDeNico.Verduleria.Dto.Response.Empleado.EmpleadoResponse;
import com.LoDeNico.Verduleria.Dto.Response.Empleado.HorarioResponse;
import com.LoDeNico.Verduleria.Dto.Response.Empleado.SignUpResponse;
import com.LoDeNico.Verduleria.Entity.Empleado.Empleado;
import com.LoDeNico.Verduleria.Entity.Empleado.Horario;
import com.LoDeNico.Verduleria.Entity.Empleado.Persona;
import com.LoDeNico.Verduleria.Repository.Empleado.EmpleadoRepository;
import com.LoDeNico.Verduleria.Repository.Empleado.PersonaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

    @Autowired
    private final EmpleadoRepository empleadoRepository;
    private final PersonaRepository personaRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository, PersonaRepository personaRepository) {
        this.empleadoRepository = empleadoRepository;
        this.personaRepository = personaRepository;
    }

    private EmpleadoResponse createEmpleadoResponse(Empleado empleado){
        EmpleadoResponse empleadoResponse=new EmpleadoResponse();
        List<HorarioResponse> horarioResponseList = new ArrayList<>();

        empleadoResponse.setId(empleado.getEId());
        empleadoResponse.setNombre(empleado.getPersona().getNombre());
        empleadoResponse.setApellido(empleado.getPersona().getApellido());
        empleadoResponse.setCodArea(empleado.getPersona().getCodArea());
        empleadoResponse.setTel(empleado.getPersona().getTel());
        empleadoResponse.setMail(empleado.getMail());
        empleadoResponse.setContra(empleado.getContra());

        if(empleado.getHorarios().isEmpty()){
            empleadoResponse.setHorarioResponseList(horarioResponseList);
        }else{
            for(Horario horario: empleado.getHorarios()) {

                HorarioResponse horarioResponse = new HorarioResponse();
                horarioResponse.setId(horario.getHId());
                horarioResponse.setNombre(empleado.getPersona().getNombre());
                horarioResponse.setApellido(empleado.getPersona().getApellido());
                horarioResponse.setDia(horario.getDia());
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
        if(empleadoRequest.getCodArea()<=0) b=true;
        if(empleadoRequest.getTel()<=0) b=true;
        if(!b){
            Empleado empleado=new Empleado();
            Persona persona = new Persona();
            Optional<Persona> personaOptional = personaRepository.findByNombreAndApellidoAndCodAreaAndTel(
                    empleadoRequest.getNombre(),
                    empleadoRequest.getApellido(),
                    empleadoRequest.getCodArea(),
                    empleadoRequest.getTel()
            );
            if(personaOptional.isEmpty()){
                persona.setNombre(empleadoRequest.getNombre());
                persona.setApellido(empleadoRequest.getApellido());
                persona.setCodArea(empleadoRequest.getCodArea());
                persona.setTel(empleadoRequest.getTel());
            }else persona=personaOptional.get();
            empleado.setPersona(persona);
            empleado.setMail(empleadoRequest.getMail());
            empleado.setContra(empleadoRequest.getContra());
            empleado.setHorarios(new ArrayList<>());
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

    public EmpleadoResponse updateEmpleado(EmpleadoRequest empleadoRequest, Long id){
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(id);
        if(empleadoOptional.isPresent()){
            Empleado empleado = empleadoOptional.get();
            boolean b=false;
            if(empleadoRequest.getNombre().isBlank()) b=true;
            if(empleadoRequest.getApellido().isBlank()) b=true;
            if(empleadoRequest.getMail().isBlank()) b=true;
            if(empleadoRequest.getContra().isBlank()) b=true;
            else if(empleadoRequest.getContra().length()<8) b=true;
            if(empleadoRequest.getCodArea()<=0) b=true;
            if(empleadoRequest.getTel()<=0) b=true;
            if(!b){
                empleado.getPersona().setNombre(empleadoRequest.getNombre());
                empleado.getPersona().setApellido(empleadoRequest.getApellido());
                empleado.setMail(empleadoRequest.getMail());
                empleado.setContra(empleadoRequest.getContra());
                empleado.getPersona().setCodArea(empleadoRequest.getCodArea());
                empleado.getPersona().setTel(empleadoRequest.getTel());
                empleado = empleadoRepository.save(empleado);
                return createEmpleadoResponse(empleado);
            }else{
                EmpleadoResponse empleadoResponse=new EmpleadoResponse();
                empleadoResponse.setId(1003L);
                empleadoResponse.setNombre("Error");
                return empleadoResponse;
            }
        }else{
            EmpleadoResponse empleadoResponse=new EmpleadoResponse();
            empleadoResponse.setId(1002L);
            empleadoResponse.setNombre("Error");
            return empleadoResponse;
        }
    }

    @PostConstruct
    public void inicializarEmpleado() {
        if(empleadoRepository.findAll().isEmpty()){
            Empleado empleado = new Empleado();
            empleado.setPersona(new Persona());
            empleado.getPersona().setNombre("Bruno");
            empleado.getPersona().setApellido("Petti");
            empleado.setMail("brunonp03@gmail.com");
            empleado.setContra("nrn12345");
            empleado.getPersona().setCodArea(2964);
            empleado.getPersona().setTel(492543);
            empleado = empleadoRepository.save(empleado);
        }
    }

    public boolean signUp(SignUpRequest signUpRequest){
        List<Empleado> empleadoList = empleadoRepository.findByContra(signUpRequest.getDatos_cuenta());
        return !empleadoList.isEmpty();
    }

    public SignUpResponse recover(SignUpRequest signUpRequest){
        Optional<Empleado> empleadoOptional = empleadoRepository.findByMail(signUpRequest.getDatos_cuenta());
        if(empleadoOptional.isPresent()){
            Empleado empleado = empleadoOptional.get();
            return new SignUpResponse(empleado.getEId(), empleado.getPersona().getNombre(), empleado.getPersona().getApellido());
        }
        else return new SignUpResponse(-1L,"","");
    }

    public boolean newContra(SignUpRequest signUpRequest, Long id){
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(id);
        if(empleadoOptional.isPresent()){
            Empleado empleado = empleadoOptional.get();
            empleado.setContra(signUpRequest.getDatos_cuenta());
            empleadoRepository.save(empleado);
            return true;
        }else return false;
    }


}
