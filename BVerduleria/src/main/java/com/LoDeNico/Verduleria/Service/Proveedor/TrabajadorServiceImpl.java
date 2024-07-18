package com.LoDeNico.Verduleria.Service.Proveedor;

import com.LoDeNico.Verduleria.Dto.Request.Proveedor.TrabajadorRequest;
import com.LoDeNico.Verduleria.Dto.Response.Proveedor.TrabajadorResponse;
import com.LoDeNico.Verduleria.Entity.Empleado.Persona;
import com.LoDeNico.Verduleria.Entity.Proveedor.Proveedor;
import com.LoDeNico.Verduleria.Entity.Proveedor.Trabajador;
import com.LoDeNico.Verduleria.Repository.Proveedor.ProveedorRepository;
import com.LoDeNico.Verduleria.Repository.Proveedor.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {

    @Autowired
    private final TrabajadorRepository trabajadorRepository;
    private final ProveedorRepository proveedorRepository;

    public TrabajadorServiceImpl(TrabajadorRepository trabajadorRepository, ProveedorRepository proveedorRepository) {
        this.trabajadorRepository = trabajadorRepository;
        this.proveedorRepository = proveedorRepository;
    }

    private TrabajadorResponse createTrabajadorResponse(Trabajador trabajador){
        return new TrabajadorResponse(trabajador.getId(),
                trabajador.getPuesto(),
                trabajador.getPersona().getNombre(),
                trabajador.getPersona().getApellido(),
                trabajador.getPersona().getCodArea(),
                trabajador.getPersona().getTel(),
                trabajador.getProveedor().getNegocio());
    }

    public TrabajadorResponse getTrabajador(Long id){
        Optional<Trabajador> trabajadorOptional = trabajadorRepository.findById(id);
        if(trabajadorOptional.isPresent()){
            Trabajador trabajador = trabajadorOptional.get();
            return createTrabajadorResponse(trabajador);
        }else{
            return new TrabajadorResponse(-1L,"","","",1002,0,"");
        }
    }

    public List<TrabajadorResponse> getTrabajadorList(){
        List<Trabajador> trabajadorList = trabajadorRepository.findAll();
        List<TrabajadorResponse> trabajadorResponseList = new ArrayList<>();
        if(!trabajadorList.isEmpty()){
            for (Trabajador trabajador: trabajadorList){
                trabajadorResponseList.add(createTrabajadorResponse(trabajador));
            }
        }else{
            TrabajadorResponse trabajadorResponse = new TrabajadorResponse(-1L,"","","",1001,0,"");
            trabajadorResponseList.add(trabajadorResponse);
        }
        return trabajadorResponseList;
    }

    public int deleteTrabajador(Long id){
        Optional<Trabajador> trabajadorOptional = trabajadorRepository.findById(id);
        if(trabajadorOptional.isPresent()){
            trabajadorRepository.deleteById(id);
            return 0;
        }else{
            return 1002;
        }
    }

    public TrabajadorResponse createTrabajador(TrabajadorRequest trabajadorRequest){
        boolean b = true;
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(trabajadorRequest.getIdP());
        if(proveedorOptional.isEmpty()) b = false;
        if(trabajadorRequest.getPuesto().isBlank()) b = false;
        if(trabajadorRequest.getNombre().isBlank()) b = false;
        if(trabajadorRequest.getApellido().isBlank()) b = false;
        if(trabajadorRequest.getCodArea() >= 0) b = false;
        if(trabajadorRequest.getTel() >= 0) b = false;

        if(b){
            Trabajador trabajador = new Trabajador();
            trabajador.setProveedor(proveedorOptional.get());
            trabajador.setPuesto(trabajadorRequest.getPuesto());

            Persona persona = new Persona();
            if(trabajadorRequest.getId()!=-1) persona.setId(trabajador.getId());
            persona.setNombre(trabajadorRequest.getNombre());
            persona.setApellido(trabajadorRequest.getApellido());
            persona.setCodArea(trabajadorRequest.getCodArea());
            persona.setTel(trabajadorRequest.getTel());

            trabajador.setPersona(persona);

            trabajador =  trabajadorRepository.save(trabajador);

            return createTrabajadorResponse(trabajador);

        }else{
            return new TrabajadorResponse(-1L,"","","",1003,0,"");
        }
    }

    public TrabajadorResponse updateTrabajador(TrabajadorRequest trabajadorRequest, Long id){
        boolean b = true;
        Optional<Trabajador> trabajadorOptional = trabajadorRepository.findById(id);
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(trabajadorRequest.getIdP());
        if(proveedorOptional.isEmpty()) b = false;
        if(trabajadorOptional.isEmpty()) b = false;
        if(trabajadorRequest.getPuesto().isBlank()) b = false;
        if(trabajadorRequest.getNombre().isBlank()) b = false;
        if(trabajadorRequest.getApellido().isBlank()) b = false;
        if(trabajadorRequest.getCodArea() >= 0) b = false;
        if(trabajadorRequest.getTel() >= 0) b = false;

        if(b){
            Trabajador trabajador = trabajadorOptional.get();
            trabajador.setProveedor(proveedorOptional.get());
            trabajador.setPuesto(trabajadorRequest.getPuesto());

            Persona persona = new Persona();
            if(trabajadorRequest.getId()!=-1) persona.setId(trabajador.getId());
            persona.setNombre(trabajadorRequest.getNombre());
            persona.setApellido(trabajadorRequest.getApellido());
            persona.setCodArea(trabajadorRequest.getCodArea());
            persona.setTel(trabajadorRequest.getTel());

            trabajador.setPersona(persona);

            trabajador =  trabajadorRepository.save(trabajador);

            return createTrabajadorResponse(trabajador);

        }else{
            return new TrabajadorResponse(-1L,"","","",1003,0,"");
        }

    }
/*
    public List<TrabajadorResponse> getTrabajadorByNombre(String nombre){
        List<Trabajador> trabajadorList = trabajadorRepository.findByNombre(nombre);
        List<TrabajadorResponse> trabajadorResponseList = new ArrayList<>();
        if(!trabajadorList.isEmpty()){
            for (Trabajador trabajador: trabajadorList){
                trabajadorResponseList.add(createTrabajadorResponse(trabajador));
            }
        }else{
            TrabajadorResponse trabajadorResponse = new TrabajadorResponse(-1L,"","","",1001,0,"");
            trabajadorResponseList.add(trabajadorResponse);
        }
        return trabajadorResponseList;
    }

    public List<TrabajadorResponse> getTrabajadorByApellido(String apellido){
        List<Trabajador> trabajadorList = trabajadorRepository.findByApellido(apellido);
        List<TrabajadorResponse> trabajadorResponseList = new ArrayList<>();
        if(!trabajadorList.isEmpty()){
            for (Trabajador trabajador: trabajadorList){
                trabajadorResponseList.add(createTrabajadorResponse(trabajador));
            }
        }else{
            TrabajadorResponse trabajadorResponse = new TrabajadorResponse(-1L,"","","",1001,0,"");
            trabajadorResponseList.add(trabajadorResponse);
        }
        return trabajadorResponseList;
    }
*/
    public List<TrabajadorResponse> getTrabajadorByPuesto(String puesto){
        List<Trabajador> trabajadorList = trabajadorRepository.findByPuesto(puesto);
        List<TrabajadorResponse> trabajadorResponseList = new ArrayList<>();
        if(!trabajadorList.isEmpty()){
            for (Trabajador trabajador: trabajadorList){
                trabajadorResponseList.add(createTrabajadorResponse(trabajador));
            }
        }else{
            TrabajadorResponse trabajadorResponse = new TrabajadorResponse(-1L,"","","",1001,0,"");
            trabajadorResponseList.add(trabajadorResponse);
        }
        return trabajadorResponseList;
    }

}
