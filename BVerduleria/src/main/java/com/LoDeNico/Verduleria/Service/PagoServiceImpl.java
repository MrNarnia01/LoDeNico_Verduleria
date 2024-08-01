package com.LoDeNico.Verduleria.Service;

import com.LoDeNico.Verduleria.Dto.Request.MontoRequest;
import com.LoDeNico.Verduleria.Dto.Request.PagoResquest;
import com.LoDeNico.Verduleria.Dto.Response.PagoResponse;
import com.LoDeNico.Verduleria.Entity.Pago;
import com.LoDeNico.Verduleria.Entity.Producto.Boleta;
import com.LoDeNico.Verduleria.Repository.PagoRepository;
import com.LoDeNico.Verduleria.Repository.Producto.BoletaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PagoServiceImpl implements PagoService{
    @Autowired
    private final PagoRepository pagoRepository;
    private final BoletaRepository boletaRepository;

    public PagoServiceImpl(PagoRepository pagoRepository, BoletaRepository boletaRepository) {
        this.pagoRepository = pagoRepository;
        this.boletaRepository = boletaRepository;
    }

    private PagoResponse createPagoResponse(Pago pago){
        return new PagoResponse(
                pago.getId(),
                pago.getBoleta().getId(),
                pago.getTipo(),
                pago.getFPago(),
                pago.getMonto()
        );
    }

    public PagoResponse getPago(Long id){
        Optional<Pago> pagoOptional = pagoRepository.findById(id);
        if (pagoOptional.isPresent()){
            return createPagoResponse(pagoOptional.get());
        }else {
            return new PagoResponse(-1L,0L,"",null,1002);
        }
    }

    public List<PagoResponse> getPagoList(){
        List<Pago> pagoList = pagoRepository.findAll();
        List<PagoResponse> pagoResponseList = new ArrayList<>();
        if (!pagoList.isEmpty()){
            for (Pago p: pagoList){
                pagoResponseList.add(createPagoResponse(p));
            }
        }else{
            pagoResponseList.add(new PagoResponse(-1L,0L,"",null,1001));
        }
        return pagoResponseList;
    }

    public int deletePago(Long id){
        Optional<Pago> pagoOptional = pagoRepository.findById(id);
        if (pagoOptional.isPresent()){
            Optional<Boleta> boletaOptional = boletaRepository.findById(pagoOptional.get().getBoleta().getId());
            Boleta boleta = boletaOptional.get();
            if(boleta.isPaga())     boleta.setPaga(false);
            boletaRepository.save(boleta);
            pagoRepository.deleteById(id);
            return 0;
        }else {
            return 1002;
        }
    }

    public PagoResponse createPago(PagoResquest pagoResquest){
        boolean b = true;
        Optional<Boleta> boletaOptional = boletaRepository.findById(pagoResquest.getIdB());

        if (boletaOptional.isEmpty())   b = false;
        if (pagoResquest.getTipo().isBlank()) b = false;
        if (pagoResquest.getMonto()<=0) b = false;

        if (b){
            Pago pago = new Pago();
            pago.setTipo(pagoResquest.getTipo());
            pago.setMonto(pagoResquest.getMonto());
            pago.setBoleta(boletaOptional.get());
            pago = pagoRepository.save(pago);

            boletaOptional = boletaRepository.findById(pagoResquest.getIdB());
            Boleta boleta = boletaOptional.get();
            if(boleta.allPagado()){
                boleta.setPaga(true);
            }
            boletaRepository.save(boleta);

            return createPagoResponse(pago);
        }else{
            return new PagoResponse(-1L,0L,"",null,1003);
        }
    }

    public PagoResponse updatePago(PagoResquest pagoResquest, Long id){
        boolean b = true;

        Optional<Pago> pagoOptional = pagoRepository.findById(id);
        if (pagoOptional.isEmpty())     return new PagoResponse(-1L,0L,"",null,1002);

        Optional<Boleta> boletaOptional = boletaRepository.findById(pagoResquest.getIdB());

        if (boletaOptional.isEmpty())   b = false;
        if (pagoResquest.getTipo().isBlank()) b = false;
        if (pagoResquest.getMonto()<=0) b = false;

        if (b){
            Pago pago = pagoOptional.get();
            pago.setTipo(pagoResquest.getTipo());
            pago.setMonto(pagoResquest.getMonto());
            pago = pagoRepository.save(pago);

            boletaOptional = boletaRepository.findById(pagoResquest.getIdB());
            Boleta boleta = boletaOptional.get();
            if(boleta.allPagado()){
                boleta.setPaga(true);
            }else{
                boleta.setPaga(false);
            }
            boletaRepository.save(boleta);

            return createPagoResponse(pago);
        }else{
            return new PagoResponse(-1L,0L,"",null,1003);
        }
    }

    public List<PagoResponse> getPagoListBus( String tipo, MontoRequest montoRequest){
        List<Pago> pagoList = new ArrayList<>();
        if(montoRequest.getM1()==-1)    pagoList = pagoRepository.findAll();
        else    pagoList = pagoRepository.serchByMonto(montoRequest.getM1(), montoRequest.getM2());

        if (tipo!=null){
            List<Pago> tipoList = pagoRepository.findByTipo(tipo);
            for (int i = 0; i < pagoList.stream().count(); i++) {
                boolean b = false;
                for (int j = 0; j < tipoList.stream().count(); j++) {
                    if (pagoList.get(i).equals(tipoList.get(j)))    b=true;
                }
                if(!b){
                    pagoList.remove(i);
                    i--;
                }
            }
        }

        List<PagoResponse> pagoResponseList = new ArrayList<>();
        if (!pagoList.isEmpty()){
            for (Pago p: pagoList){
                pagoResponseList.add(createPagoResponse(p));
            }
        }else{
            pagoResponseList.add(new PagoResponse(-1L,0L,"",null,1001));
        }
        return pagoResponseList;
    }


}
