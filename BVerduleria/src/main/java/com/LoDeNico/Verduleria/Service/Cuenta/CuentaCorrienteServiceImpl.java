package com.LoDeNico.Verduleria.Service.Cuenta;

import com.LoDeNico.Verduleria.Dto.Request.Cuenta.CuentaCorrienteRequest;
import com.LoDeNico.Verduleria.Dto.Request.FechaRequest;
import com.LoDeNico.Verduleria.Dto.Request.MontoRequest;
import com.LoDeNico.Verduleria.Dto.Response.Cuenta.CuentaCorrienteResponse;
import com.LoDeNico.Verduleria.Entity.Cuenta.Cliente;
import com.LoDeNico.Verduleria.Entity.Cuenta.CuentaCorriente;
import com.LoDeNico.Verduleria.Repository.Cuenta.ClienteRepository;
import com.LoDeNico.Verduleria.Repository.Cuenta.CuentaCorrienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaCorrienteServiceImpl implements CuentaCorrienteService{
    @Autowired
    private final CuentaCorrienteRepository cuentaCorrienteRepository;
    private final ClienteRepository clienteRepository;

    public CuentaCorrienteServiceImpl(CuentaCorrienteRepository cuentaCorrienteRepository, ClienteRepository clienteRepository) {
        this.cuentaCorrienteRepository = cuentaCorrienteRepository;
        this.clienteRepository = clienteRepository;
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

    public List<CuentaCorrienteResponse> getCuentaCorrienteListByMonto(MontoRequest montoRequest){
        List<CuentaCorriente> cuentaCorrienteList = cuentaCorrienteRepository.serchByMonto(montoRequest.getM1(),montoRequest.getM2());
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
            if (cuentaCorriente.getFRegistro().equals(cuentaCorriente.getFPago())){
                cuentaCorrienteRepository.deleteById(id);
                return 0;
            }
            return 1004;
        }else   return 1002;
    }

    public CuentaCorrienteResponse createCuentaCorriente(CuentaCorrienteRequest cuentaCorrienteRequest){
        boolean b = true;
        Optional<Cliente> clienteOptional = clienteRepository.findById(cuentaCorrienteRequest.getIdC());
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

    public CuentaCorrienteResponse updateCuentaCorriente(CuentaCorrienteRequest cuentaCorrienteRequest, Long id){
        boolean b = true;
        CuentaCorriente cuentaCorriente;
        Optional<CuentaCorriente> cuentaCorrienteOptional = cuentaCorrienteRepository.findById(id);
        if(cuentaCorrienteOptional.isPresent()){
            cuentaCorriente =  cuentaCorrienteOptional.get();
            if(!cuentaCorriente.getFRegistro().equals(cuentaCorriente.getFPago())) return new CuentaCorrienteResponse(-1L,"","",1004,null,null);
        }
        else return new CuentaCorrienteResponse(-1L,"","",1002,null,null);
        Optional<Cliente> clienteOptional = clienteRepository.findById(cuentaCorrienteRequest.getIdC());
        if(clienteOptional.isEmpty())   b = false;
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
            if(cuentaCorriente.getFRegistro().equals(cuentaCorriente.getFPago())){
                cuentaCorriente.setFPago(new Timestamp(System.currentTimeMillis()));
                cuentaCorrienteRepository.save(cuentaCorriente);
                return createCuenteCorrienteResponse(cuentaCorriente);
            }
            else return new CuentaCorrienteResponse(-1L,"","",1004,null,null);
        }else   return new CuentaCorrienteResponse(-1L,"","",1003,null,null);
    }

    public List<CuentaCorrienteResponse> getCuentaCorrienteListByFechas(FechaRequest fechaRequest, boolean columna){
        String nColumna;
        if (columna) nColumna = "fRegisto";
        else nColumna = "fPago";
        Timestamp inicio = new Timestamp(fechaRequest.getF1().getTime());
        Timestamp cierre = new Timestamp(fechaRequest.getF2().getTime());
        List<CuentaCorriente> cuentaCorrienteList = cuentaCorrienteRepository.findByDias(inicio,cierre,nColumna);
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



}
