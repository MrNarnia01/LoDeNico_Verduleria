package com.LoDeNico.Verduleria.Service.Cierre;

import com.LoDeNico.Verduleria.Dto.Request.Cierre.BusRequest;
import com.LoDeNico.Verduleria.Dto.Request.Cierre.CierreRequest;
import com.LoDeNico.Verduleria.Entity.Cierre.CierreLote;
import com.LoDeNico.Verduleria.Repository.Cierre.CierreLoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CierreLoteServiceImpl implements CierreLoteService {
    @Autowired
    private final CierreLoteRepository cierreLoteRepository;

    public CierreLoteServiceImpl(CierreLoteRepository cierreLoteRepository) {
        this.cierreLoteRepository = cierreLoteRepository;
    }

    public CierreLote createCierre(CierreRequest cierreRequest){
        boolean b = true;

        if(cierreRequest.getMonto()<=0) b = false;

        if(b){
            CierreLote cierreLote =  new CierreLote();
            cierreLote.setMonto(cierreRequest.getMonto());
            cierreLote.setTipo(cierreRequest.isTipo());
            cierreLote = cierreLoteRepository.save(cierreLote);
            return cierreLote;
        }else{
            return new CierreLote(-1L,true,1003,null);
        }
    }

    public int deleteCierre(Long id){
        Optional<CierreLote> cierreLoteOptional = cierreLoteRepository.findById(id);
        if(cierreLoteOptional.isPresent()){
            cierreLoteRepository.deleteById(id);
            return 0;
        }else{
            return 1002;
        }
    }

    /*
        Codigos de busqueda:
            0 : Ningun filtro
            1 : Solo tipo
            2 : Ambos
            3 : Solo monto
     */

    public List<CierreLote> cierreLoteBus(BusRequest busRequest){
        List<CierreLote> cierreLoteList = new ArrayList<>();
        //Darle datos a la lista
        if(busRequest.getFechaRequest().getF1() == null){
            cierreLoteList = cierreLoteRepository.findAll();
        }else{
            cierreLoteList = cierreLoteRepository.findByDias(busRequest.getFechaRequest().getF1(),busRequest.getFechaRequest().getF2());
        }

        //Datos tipo
        if(busRequest.getCod()<=2){
            List<CierreLote> cierreTipo = cierreLoteRepository.findByTipo(busRequest.isT());
            //Filtracion
            for (int i = 0; i < cierreLoteList.stream().count(); i++) {
                boolean b = false;
                for (int j = 0; j < cierreTipo.stream().count(); j++) {
                    if (cierreLoteList.get(i).equals(cierreTipo.get(j)))    b=true;
                }
                if(!b){
                    cierreLoteList.remove(i);
                    i--;
                }
            }
        }
        //Datos monto
        if(busRequest.getCod()>=2){
            List<CierreLote> cierreMonto = cierreLoteRepository.serchByMonto(busRequest.getMontoRequest().getM1(), busRequest.getMontoRequest().getM2());
            //Filtracion
            for (int i = 0; i < cierreLoteList.stream().count(); i++) {
                boolean b = false;
                for (int j = 0; j < cierreMonto.stream().count(); j++) {
                    if (cierreLoteList.get(i).equals(cierreMonto.get(j)))    b=true;
                }
                if(!b)  cierreLoteList.remove(i);
                i--;
            }
        }
        return cierreLoteList;
    }

    public List<CierreLote> cierreLoteList(){
        return cierreLoteRepository.findAll();
    }

    public CierreLote updateCierre(CierreRequest cierreRequest, Long id){
        boolean b = true;
        Optional<CierreLote> cierreLoteOptional = cierreLoteRepository.findById(id);
        if(cierreLoteOptional.isEmpty())    return new CierreLote(-1L,true,1002,null);
        if(cierreRequest.getMonto()<=0) b = false;

        if(b){
            CierreLote cierreLote =  cierreLoteOptional.get();
            cierreLote.setMonto(cierreRequest.getMonto());
            cierreLote.setTipo(cierreRequest.isTipo());
            cierreLote = cierreLoteRepository.save(cierreLote);
            return cierreLote;
        }else{
            return new CierreLote(-1L,true,1003,null);
        }
    }



}
