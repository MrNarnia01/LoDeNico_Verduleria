package com.LoDeNico.Verduleria.Service.Cuenta;

import com.LoDeNico.Verduleria.Dto.Request.Cuenta.ClienteRequest;
import com.LoDeNico.Verduleria.Dto.Response.Cuenta.ClienteResponse;
import com.LoDeNico.Verduleria.Dto.Response.Cuenta.CuentaCorrienteResponse;
import com.LoDeNico.Verduleria.Entity.Cuenta.Cliente;
import com.LoDeNico.Verduleria.Entity.Cuenta.CuentaCorriente;
import com.LoDeNico.Verduleria.Entity.Empleado.Persona;
import com.LoDeNico.Verduleria.Repository.Cuenta.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{
    @Autowired
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    private ClienteResponse createClienteResponse(Cliente cliente){
        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setId(cliente.getId());
        clienteResponse.setNombre(cliente.getPersona().getNombre());
        clienteResponse.setApellido(cliente.getPersona().getApellido());
        clienteResponse.setCodArea(cliente.getPersona().getCodArea());
        clienteResponse.setTel(cliente.getPersona().getTel());
        clienteResponse.setCalle(cliente.getCalle());
        clienteResponse.setAltura(cliente.getAltura());

        List<CuentaCorrienteResponse> cuentaCorrienteResponseList = new ArrayList<>();
        for(CuentaCorriente cC : cliente.getCuentasCorriente()){
            CuentaCorrienteResponse cuentaCorrienteResponse =  new CuentaCorrienteResponse();
            cuentaCorrienteResponse.setId(cC.getId());
            cuentaCorrienteResponse.setNombre(clienteResponse.getNombre());
            cuentaCorrienteResponse.setApellido(clienteResponse.getApellido());
            cuentaCorrienteResponse.setMonto(cC.getMonto());
            cuentaCorrienteResponse.setFRegistro(cC.getFRegistro());
            cuentaCorrienteResponse.setFPago(cC.getFPago());
            cuentaCorrienteResponseList.add(cuentaCorrienteResponse);
        }
        clienteResponse.setCuentaCorrienteResponseList(cuentaCorrienteResponseList);
        return clienteResponse;
    }

    public ClienteResponse getCliente(Long id){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isPresent()){
            return createClienteResponse(clienteOptional.get());
        }else{
            return new ClienteResponse(-1L,"","",
                    1002,0,"",0,null);
        }
    }

    public List<ClienteResponse> getClienteList(){
        List<Cliente> clienteList = clienteRepository.findAll();
        List<ClienteResponse> clienteResponseList =  new ArrayList<>();
        if(!clienteList.isEmpty()){
            for (Cliente cliente : clienteList){
                clienteResponseList.add(
                        createClienteResponse(cliente)
                );
            }
            return clienteResponseList;
        }else{
            ClienteResponse clienteResponse =  new ClienteResponse(-1L,"","",
                    1001,0,"",0,null);
            clienteResponseList.add(clienteResponse);
            return  clienteResponseList;
        }
    }

    public List<ClienteResponse> getClienteListByNombre(String nombre){
        List<Cliente> clienteList = clienteRepository.findByNombre(nombre);
        List<ClienteResponse> clienteResponseList =  new ArrayList<>();
        if(!clienteList.isEmpty()){
            for (Cliente cliente : clienteList){
                clienteResponseList.add(
                        createClienteResponse(cliente)
                );
            }
            return clienteResponseList;
        }else{
            ClienteResponse clienteResponse =  new ClienteResponse(-1L,"","",
                    1001,0,"",0,null);
            clienteResponseList.add(clienteResponse);
            return  clienteResponseList;
        }
    }

    public List<ClienteResponse> getClienteListByApellido(String apellido){
        List<Cliente> clienteList = clienteRepository.findByApellido(apellido);
        List<ClienteResponse> clienteResponseList =  new ArrayList<>();
        if(!clienteList.isEmpty()){
            for (Cliente cliente : clienteList){
                clienteResponseList.add(
                        createClienteResponse(cliente)
                );
            }
            return clienteResponseList;
        }else{
            ClienteResponse clienteResponse =  new ClienteResponse(-1L,"","",
                    1001,0,"",0,null);
            clienteResponseList.add(clienteResponse);
            return  clienteResponseList;
        }
    }

    public List<ClienteResponse> getClienteListByCalle(String calle){
        List<Cliente> clienteList = clienteRepository.findByCalle(calle);
        List<ClienteResponse> clienteResponseList =  new ArrayList<>();
        if(!clienteList.isEmpty()){
            for (Cliente cliente : clienteList){
                clienteResponseList.add(
                        createClienteResponse(cliente)
                );
            }
            return clienteResponseList;
        }else{
            ClienteResponse clienteResponse =  new ClienteResponse(-1L,"","",
                    1001,0,"",0,null);
            clienteResponseList.add(clienteResponse);
            return  clienteResponseList;
        }
    }

    public ClienteResponse createCliente(ClienteRequest clienteRequest){
        boolean b = true;
        if (clienteRequest.getNombre().isBlank()) b = false;
        if (clienteRequest.getApellido().isBlank()) b = false;
        if (clienteRequest.getCalle().isBlank()) b = false;
        if (clienteRequest.getCodArea()<=0) b = false;
        if (clienteRequest.getTel()<=0) b = false;
        if (clienteRequest.getAltura()<=0) b = false;

        if(b){
            Cliente cliente = new Cliente();
            cliente.setCalle(cliente.getCalle());
            cliente.setAltura(cliente.getAltura());

            Persona persona = new Persona();
            if(clienteRequest.getId()!=-1) persona.setId(clienteRequest.getId());
            persona.setNombre(clienteRequest.getNombre());
            persona.setApellido(clienteRequest.getApellido());
            persona.setCodArea(clienteRequest.getCodArea());
            persona.setTel(clienteRequest.getTel());
            cliente.setPersona(persona);

            cliente = clienteRepository.save(cliente);
            return createClienteResponse(cliente);
        }else{
            return new ClienteResponse(-1L,"","",
                    1003,0,"",0,null);
        }
    }

    public int deleteCliente(Long id){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            if(cliente.getCuentasCorriente().isEmpty()){
                clienteRepository.deleteById(id);
                return 0;
            }else   return 1004;
        }else return 1002;
    }

    public ClienteResponse updateCliente(ClienteRequest clienteRequest, Long id){
        boolean b = true;
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isEmpty()) return new ClienteResponse(-1L,"","",
                1002,0,"",0,null);
        if (clienteRequest.getNombre().isBlank()) b = false;
        if (clienteRequest.getApellido().isBlank()) b = false;
        if (clienteRequest.getCalle().isBlank()) b = false;
        if (clienteRequest.getCodArea()<=0) b = false;
        if (clienteRequest.getTel()<=0) b = false;
        if (clienteRequest.getAltura()<=0) b = false;

        if(b){
            Cliente cliente = clienteOptional.get();
            cliente.setCalle(cliente.getCalle());
            cliente.setAltura(cliente.getAltura());

            Persona persona = new Persona();
            if(clienteRequest.getId()!=-1) persona.setId(clienteRequest.getId());
            persona.setNombre(clienteRequest.getNombre());
            persona.setApellido(clienteRequest.getApellido());
            persona.setCodArea(clienteRequest.getCodArea());
            persona.setTel(clienteRequest.getTel());
            cliente.setPersona(persona);

            cliente = clienteRepository.save(cliente);
            return createClienteResponse(cliente);
        }else{
            return new ClienteResponse(-1L,"","",
                    1003,0,"",0,null);
        }
    }


}
