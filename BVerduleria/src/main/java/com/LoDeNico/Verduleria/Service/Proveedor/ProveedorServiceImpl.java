package com.LoDeNico.Verduleria.Service.Proveedor;

import com.LoDeNico.Verduleria.Dto.Request.Proveedor.ProveedorRequest;
import com.LoDeNico.Verduleria.Dto.Response.Proveedor.ProveedorResponse;
import com.LoDeNico.Verduleria.Dto.Response.Proveedor.TrabajadorResponse;
import com.LoDeNico.Verduleria.Entity.Proveedor.Proveedor;
import com.LoDeNico.Verduleria.Entity.Proveedor.Trabajador;
import com.LoDeNico.Verduleria.Repository.Proveedor.ProveedorRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements ProveedorService{
    @Autowired
    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    private ProveedorResponse createProveedorResponse(Proveedor proveedor){
        ProveedorResponse proveedorResponse = new ProveedorResponse();
        proveedorResponse.setId(proveedor.getId());
        proveedorResponse.setNegocio(proveedor.getNegocio());
        proveedorResponse.setCalle(proveedor.getCalle());
        proveedorResponse.setAltura(proveedor.getAltura());

        List<TrabajadorResponse> trabajadorResponseList = new ArrayList<>();

        for (Trabajador trabajador: proveedor.getTrabajadores()){
            TrabajadorResponse trabajadorResponse = new TrabajadorResponse(
                    trabajador.getId(),
                    trabajador.getPuesto(),
                    trabajador.getPersona().getNombre(),
                    trabajador.getPersona().getApellido(),
                    trabajador.getPersona().getCodArea(),
                    trabajador.getPersona().getTel(),
                    proveedor.getNegocio());
            trabajadorResponseList.add(trabajadorResponse);
        }
        proveedorResponse.setTrabajadorResponseList(trabajadorResponseList);
        return proveedorResponse;
    }

    public ProveedorResponse getProveedor(Long id){
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
        if(proveedorOptional.isPresent()){
            Proveedor proveedor = proveedorOptional.get();
            return createProveedorResponse(proveedor);
        }else{
            return new ProveedorResponse(-1L,"","",1002,null);
        }
    }

    public List<ProveedorResponse> getProveedorList(){
        List<Proveedor> proveedorList = proveedorRepository.findAll();
        List<ProveedorResponse> proveedorResponseList = new ArrayList<>();
        if(!proveedorList.isEmpty()){
            for (Proveedor proveedor: proveedorList){
                ProveedorResponse proveedorResponse = createProveedorResponse(proveedor);
                proveedorResponseList.add(proveedorResponse);
            }
            return proveedorResponseList;
        }else {
            proveedorResponseList.add(new ProveedorResponse(-1L,"","",1001,null));
            return proveedorResponseList;
        }
    }

    public ProveedorResponse getProveedorByNegocio(String negocio){
        Optional<Proveedor> proveedorOptional = proveedorRepository.findByNegocio(negocio);
        if(proveedorOptional.isPresent()){
            Proveedor proveedor = proveedorOptional.get();
            return createProveedorResponse(proveedor);
        }else{
            return new ProveedorResponse(-1L,"","",1002,null);
        }
    }

    public int deleteProveedor(Long id){
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
        if(proveedorOptional.isPresent()){
            Proveedor proveedor = proveedorOptional.get();
            if(proveedor.getTrabajadores().isEmpty()){
               proveedorRepository.deleteById(id);
               return 0;
            }else return 1004;
        }else   return 1002;
    }

    public ProveedorResponse createProveedor(ProveedorRequest proveedorRequest){
        boolean b = true;
        if (proveedorRequest.getAltura()<0) b = false;
        if (proveedorRequest.getCalle().isBlank()) b = false;
        if (proveedorRequest.getNegocio().isBlank()) b = false;

        Optional<Proveedor> proveedorOptional = proveedorRepository.findByNegocio(proveedorRequest.getNegocio());
        if (proveedorOptional.isPresent()) b = false;

        if(b){
            Proveedor proveedor = new Proveedor();
            proveedor.setNegocio(proveedorRequest.getNegocio());
            proveedor.setCalle(proveedorRequest.getCalle());
            proveedor.setAltura(proveedorRequest.getAltura());
            proveedor = proveedorRepository.save(proveedor);
            return createProveedorResponse(proveedor);
        }else return new ProveedorResponse(-1L,"","",1003,null);
    }

    public ProveedorResponse updateProveedor(ProveedorRequest proveedorRequest, Long id){
        boolean b = true;
        Proveedor proveedor = new Proveedor();
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);

        if (proveedorOptional.isPresent())  proveedor = proveedorOptional.get();
        else b = false;

        if (proveedorRequest.getAltura()<0) b = false;
        if (proveedorRequest.getCalle().isBlank()) b = false;
        if (proveedorRequest.getNegocio().isBlank()) b = false;

        if(b){
            proveedor.setNegocio(proveedorRequest.getNegocio());
            proveedor.setCalle(proveedorRequest.getCalle());
            proveedor.setAltura(proveedorRequest.getAltura());
            proveedor = proveedorRepository.save(proveedor);
            return createProveedorResponse(proveedor);
        }else return new ProveedorResponse(-1L,"","",1003,null);
    }

}
