package com.LoDeNico.Verduleria.Service.Cuenta;

import com.LoDeNico.Verduleria.Dto.Request.MontoRequest;
import com.LoDeNico.Verduleria.Dto.Response.Cuenta.CuentaCorrienteResponse;
import com.LoDeNico.Verduleria.Entity.Cuenta.CuentaCorriente;
import com.LoDeNico.Verduleria.Repository.Cuenta.ClienteRepository;
import com.LoDeNico.Verduleria.Repository.Cuenta.CuentaCorrienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



}
