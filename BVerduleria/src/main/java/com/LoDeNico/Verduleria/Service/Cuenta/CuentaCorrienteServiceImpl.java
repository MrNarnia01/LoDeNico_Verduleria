package com.LoDeNico.Verduleria.Service.Cuenta;

import com.LoDeNico.Verduleria.Dto.Request.Cuenta.ClienteRequest;
import com.LoDeNico.Verduleria.Dto.Request.Cuenta.CuentaCorrienteRequest;
import com.LoDeNico.Verduleria.Dto.Response.Cuenta.CuentaCorrienteResponse;
import com.LoDeNico.Verduleria.Entity.Cuenta.Cliente;
import com.LoDeNico.Verduleria.Entity.Cuenta.CuentaCorriente;
import com.LoDeNico.Verduleria.Entity.Empleado.Persona;
import com.LoDeNico.Verduleria.Repository.Cuenta.ClienteRepository;
import com.LoDeNico.Verduleria.Repository.Cuenta.CuentaCorrienteRepository;
import com.LoDeNico.Verduleria.Repository.Empleado.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaCorrienteServiceImpl implements CuentaCorrienteService{
    @Autowired
    private final CuentaCorrienteRepository cuentaCorrienteRepository;
    private final ClienteRepository clienteRepository;
    private final PersonaRepository personaRepository;

    public CuentaCorrienteServiceImpl(CuentaCorrienteRepository cuentaCorrienteRepository, ClienteRepository clienteRepository, PersonaRepository personaRepository) {
        this.cuentaCorrienteRepository = cuentaCorrienteRepository;
        this.clienteRepository = clienteRepository;
        this.personaRepository = personaRepository;
    }

    private CuentaCorrienteResponse createCuenteCorrienteResponse(CuentaCorriente cuentaCorriente){
        CuentaCorrienteResponse cuentaCorrienteResponse = new CuentaCorrienteResponse(cuentaCorriente.getId(),
                cuentaCorriente.getCliente().getPersona().getNombre(),
                cuentaCorriente.getCliente().getPersona().getNombre(),
                cuentaCorriente.getMonto(),
                cuentaCorriente.getFRegistro(),
                cuentaCorriente.getFPago());
        return cuentaCorrienteResponse;
    }

    public CuentaCorrienteResponse getCuentaCorriente(Long id){
        Optional<CuentaCorriente> cuentaCorrienteOptional = cuentaCorrienteRepository.findById(id);
        if (cuentaCorrienteOptional.isPresent()){
            return createCuenteCorrienteResponse(cuentaCorrienteOptional.get());
        }else return new CuentaCorrienteResponse(-1L,"","",1002,null,null);
    }

    public List<CuentaCorrienteResponse> getCuentaCorrienteList(){
        List<CuentaCorriente> cuentaCorrienteList = cuentaCorrienteRepository.findAll();
        List<CuentaCorrienteResponse> cuentaCorrienteResponseList = new ArrayList<>();
        if(!cuentaCorrienteList.isEmpty()){
            for (CuentaCorriente cC : cuentaCorrienteList){
                CuentaCorrienteResponse ccR = createCuenteCorrienteResponse(cC);
                cuentaCorrienteResponseList.add(ccR);
            }
            return cuentaCorrienteResponseList;
        }else{
          CuentaCorrienteResponse cuentaCorrienteResponse = new CuentaCorrienteResponse();
          cuentaCorrienteResponse.setId(-1L);
          cuentaCorrienteResponse.setMonto(1001);
          cuentaCorrienteResponseList.add(cuentaCorrienteResponse);
          return cuentaCorrienteResponseList;
        }
    }

    public int deleteCuentaCorriente(Long id){
        Optional<CuentaCorriente> cuentaCorrienteOptional = cuentaCorrienteRepository.findById(id);
        if(cuentaCorrienteOptional.isPresent()){
            CuentaCorriente cuentaCorriente =  cuentaCorrienteOptional.get();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            if (format.format(cuentaCorriente.getFRegistro()).equals(format.format(cuentaCorriente.getFPago()))){
                cuentaCorrienteRepository.deleteById(id);
                return 0;
            }
            return 1004;
        }else   return 1002;
    }

    public CuentaCorrienteResponse createCuentaCorrienteWithCliente(CuentaCorrienteRequest cuentaCorrienteRequest){
        boolean b = true;
        Optional<Cliente> clienteOptional = clienteRepository.findById(cuentaCorrienteRequest.getId());
        if(clienteOptional.isEmpty())   b = false;
        if (cuentaCorrienteRequest.getMonto()<=0) b = false;
        if(b){
            CuentaCorriente cuentaCorriente =  new CuentaCorriente();
            cuentaCorriente.setCliente(clienteOptional.get());
            cuentaCorriente.setMonto(cuentaCorrienteRequest.getMonto());
            cuentaCorriente = cuentaCorrienteRepository.save(cuentaCorriente);
            return createCuenteCorrienteResponse(cuentaCorriente);
        }else return new CuentaCorrienteResponse(-1L,"","",1003,null,null);
    }

    public CuentaCorrienteResponse createCuentaCorrienteWithoutCliente(ClienteRequest clienteRequest){
        boolean b = true;
        if (clienteRequest.getMonto()<=0) b = false;
        if (clienteRequest.getAltura()<=0) b = false;
        if (clienteRequest.getTel()<=0) b = false;
        if (clienteRequest.getCodArea()<=0) b = false;
        if (clienteRequest.getNombre().isBlank()) b = false;
        if (clienteRequest.getApellido().isBlank()) b = false;
        if (clienteRequest.getCalle().isBlank()) b = false;

        if(b){
            Optional<Persona> personaOptional = personaRepository.findByNombreAndApellidoAndCodAreaAndTel(
                    clienteRequest.getNombre(),
                    clienteRequest.getApellido(),
                    clienteRequest.getCodArea(),
                    clienteRequest.getTel()
            );

            Persona p=new Persona();
            if(personaOptional.isEmpty()){
                p.setNombre(clienteRequest.getNombre());
                p.setApellido(clienteRequest.getApellido());
                p.setCodArea(clienteRequest.getCodArea());
                p.setTel(clienteRequest.getTel());
            }else p=personaOptional.get();

            Cliente c = new Cliente();
            c.setPersona(p);
            c.setCalle(clienteRequest.getCalle());
            c.setAltura(clienteRequest.getAltura());

            CuentaCorriente cuentaCorriente =  new CuentaCorriente();
            cuentaCorriente.setCliente(c);
            cuentaCorriente.setMonto(clienteRequest.getMonto());
            cuentaCorriente = cuentaCorrienteRepository.save(cuentaCorriente);
            return createCuenteCorrienteResponse(cuentaCorriente);
        }else return new CuentaCorrienteResponse(-1L,"","",1003,null,null);
    }

    public CuentaCorrienteResponse updateCuentaCorriente(CuentaCorrienteRequest cuentaCorrienteRequest){
        boolean b = true;
        CuentaCorriente cuentaCorriente;
        Optional<CuentaCorriente> cuentaCorrienteOptional = cuentaCorrienteRepository.findById(cuentaCorrienteRequest.getId());
        if(cuentaCorrienteOptional.isPresent()){
            cuentaCorriente =  cuentaCorrienteOptional.get();
            SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            if(!formato.format(cuentaCorriente.getFRegistro()).equals(formato.format(cuentaCorriente.getFPago()))) return new CuentaCorrienteResponse(-1L,"","",1004,null,null);
        }
        else return new CuentaCorrienteResponse(-1L,"","",1002,null,null);
        if (cuentaCorrienteRequest.getMonto()<=0) b = false;
        if(b){
            cuentaCorriente.setMonto(cuentaCorrienteRequest.getMonto());
            cuentaCorriente = cuentaCorrienteRepository.save(cuentaCorriente);
            return createCuenteCorrienteResponse(cuentaCorriente);
        }else return new CuentaCorrienteResponse(-1L,"","",1003,null,null);
    }

    public CuentaCorrienteResponse updateFPago(Long id){
        Optional<CuentaCorriente> cuentaCorrienteOptional =  cuentaCorrienteRepository.findById(id);
        if (cuentaCorrienteOptional.isPresent()){
            CuentaCorriente cuentaCorriente = cuentaCorrienteOptional.get();
            SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            if(formato.format(cuentaCorriente.getFRegistro()).equals(formato.format(cuentaCorriente.getFPago()))){
                cuentaCorriente.setFPago(new Timestamp(System.currentTimeMillis()));
                cuentaCorrienteRepository.save(cuentaCorriente);
                return createCuenteCorrienteResponse(cuentaCorriente);
            }
            else return new CuentaCorrienteResponse(-1L,"","",1004,null,null);
        }else   return new CuentaCorrienteResponse(-1L,"","",1003,null,null);
    }




}
